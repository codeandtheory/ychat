package co.yml.ychat.ducai.entrypoint.impl

import co.yml.ychat.YChat
import co.yml.ychat.ducai.domain.model.DucAiCompletionParams
import co.yml.ychat.ducai.domain.usecases.CompletionDucAIUseCase
import co.yml.ychat.ducai.entrypoint.features.DucAICompletions
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

    override fun execute(callback: YChat.Callback<String>) {
        scope.launch {
            runCatching { execute() }
                .onSuccess { callback.onSuccess(it) }
                .onFailure { callback.onError(it) }
        }
    }
}