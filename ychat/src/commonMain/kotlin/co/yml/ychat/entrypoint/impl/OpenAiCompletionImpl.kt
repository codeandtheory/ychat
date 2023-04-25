package co.yml.ychat.entrypoint.impl

import co.yml.ychat.YChat
import co.yml.ychat.domain.model.CompletionParams
import co.yml.ychat.domain.usecases.CompletionUseCase
import co.yml.ychat.entrypoint.features.OpenAiCompletion
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

internal class OpenAiCompletionImpl(
    private val dispatcher: CoroutineDispatcher,
    private val completionUseCase: CompletionUseCase
) : OpenAiCompletion {

    private val scope by lazy { CoroutineScope(SupervisorJob() + dispatcher) }

    private var params: CompletionParams = CompletionParams()

    override fun setInput(input: String): OpenAiCompletion {
        this.params.prompt = input
        return this
    }

    override fun setModel(model: String): OpenAiCompletion {
        this.params.model = model
        return this
    }

    override fun setMaxTokens(tokens: Int): OpenAiCompletion {
        this.params.maxTokens = tokens
        return this
    }

    override fun setTemperature(temperature: Double): OpenAiCompletion {
        this.params.temperature = temperature
        return this
    }

    override fun setTopP(topP: Double): OpenAiCompletion {
        this.params.topP = topP
        return this
    }

    override fun saveHistory(isSaveHistory: Boolean): OpenAiCompletion {
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

    override fun execute(callback: YChat.Callback<String>) {
        scope.launch {
            runCatching { execute() }
                .onSuccess { callback.onSuccess(it) }
                .onFailure { callback.onError(it) }
        }
    }
}
