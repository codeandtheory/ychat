package co.yml.ychat.entrypoint.impl

import co.yml.ychat.YChat
import co.yml.ychat.domain.model.CompletionParams
import co.yml.ychat.domain.usecases.CompletionUseCase
import co.yml.ychat.entrypoint.features.Completion
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

internal class CompletionImpl(private val completionUseCase: CompletionUseCase) : Completion {

    private var params: CompletionParams = CompletionParams()

    override fun setInput(input: String): Completion {
        this.params.prompt = input
        return this
    }

    override fun setModel(model: String): Completion {
        this.params.model = model
        return this
    }

    override fun setMaxTokens(tokens: Int): Completion {
        this.params.maxTokens = tokens
        return this
    }

    override fun setTemperature(temperature: Double): Completion {
        this.params.temperature = temperature
        return this
    }

    override fun setTopP(topP: Double): Completion {
        this.params.topP = topP
        return this
    }

    override fun saveHistory(isSaveHistory: Boolean): Completion {
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
        MainScope().launch {
            runCatching { execute() }
                .onSuccess { callback.onSuccess(it) }
                .onFailure { callback.onError(it) }
        }
    }
}
