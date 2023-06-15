package co.yml.openai.provider.entrypoint.impl

import co.yml.openai.provider.OpenAI
import co.yml.openai.provider.entrypoint.features.OpenAICompletion
import co.yml.openai.provider.domain.model.CompletionParams
import co.yml.openai.provider.domain.usecases.CompletionUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

internal class OpenAICompletionImpl(
    private val dispatcher: CoroutineDispatcher,
    private val completionUseCase: CompletionUseCase
) : OpenAICompletion {

    private val scope by lazy { CoroutineScope(SupervisorJob() + dispatcher) }

    private var params: CompletionParams = CompletionParams()

    override fun setInput(input: String): OpenAICompletion {
        this.params.prompt = input
        return this
    }

    override fun setModel(model: String): OpenAICompletion {
        this.params.model = model
        return this
    }

    override fun setMaxTokens(tokens: Int): OpenAICompletion {
        this.params.maxTokens = tokens
        return this
    }

    override fun setTemperature(temperature: Double): OpenAICompletion {
        this.params.temperature = temperature
        return this
    }

    override fun setTopP(topP: Double): OpenAICompletion {
        this.params.topP = topP
        return this
    }

    override fun saveHistory(isSaveHistory: Boolean): OpenAICompletion {
        this.params.enableChatStorage = isSaveHistory
        return this
    }

    override suspend fun execute(): String {
        return completionUseCase.completion(params)
            .choices
            .first()
            .text
            .trim()
    }

    override fun execute(callback: OpenAI.Callback<String>) {
        scope.launch {
            runCatching { execute() }
                .onSuccess { callback.onSuccess(it) }
                .onFailure { callback.onError(it) }
        }
    }
}
