package co.yml.ducai.provider.entrypoint.impl

import co.yml.ducai.provider.DucAI
import co.yml.ducai.provider.domain.model.DucAiCompletionParams
import co.yml.ducai.provider.domain.usecases.CompletionDucAIUseCase
import co.yml.ducai.provider.entrypoint.features.DucAICompletions
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

internal class DucAICompletionsImpl(
    private val completionUseCase: CompletionDucAIUseCase,
    private val dispatcher: CoroutineDispatcher
) : DucAICompletions {

    private val scope by lazy { CoroutineScope(SupervisorJob() + dispatcher) }

    private var params: DucAiCompletionParams = DucAiCompletionParams()

    override fun setInput(input: String): DucAICompletions {
        this.params.data = input
        return this
    }

    override suspend fun execute() = completionUseCase.completion(params)
        .data
        .trim()

    override fun execute(callback: DucAI.Callback<String>) {
        scope.launch {
            runCatching { execute() }
                .onSuccess { callback.onSuccess(it) }
                .onFailure { callback.onError(it) }
        }
    }
}
