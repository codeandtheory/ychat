package co.yml.openai.provider.entrypoint.impl

import co.yml.openai.provider.OpenAi
import co.yml.openai.provider.entrypoint.features.OpenAiChatCompletions
import co.yml.openai.provider.domain.model.ChatCompletionsParams
import co.yml.openai.provider.domain.model.ChatMessage
import co.yml.openai.provider.domain.usecases.ChatCompletionsUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

internal class OpenAiChatCompletionsImpl(
    private val dispatcher: CoroutineDispatcher,
    private val chatCompletionsUseCase: ChatCompletionsUseCase,
) : OpenAiChatCompletions {

    private val scope by lazy { CoroutineScope(SupervisorJob() + dispatcher) }

    private var params: ChatCompletionsParams = ChatCompletionsParams()

    override fun setModel(model: String): OpenAiChatCompletions {
        params.model = model
        return this
    }

    override fun setTopP(topP: Double): OpenAiChatCompletions {
        params.topP = topP
        return this
    }

    override fun setTemperature(temperature: Double): OpenAiChatCompletions {
        params.temperature = temperature
        return this
    }

    override fun setMaxResults(results: Int): OpenAiChatCompletions {
        params.maxResults = results
        return this
    }

    override fun setMaxTokens(tokens: Int): OpenAiChatCompletions {
        params.maxTokens = tokens
        return this
    }

    override fun addMessage(role: String, content: String): OpenAiChatCompletions {
        params.messages.add(ChatMessage(role, content))
        return this
    }

    override suspend fun execute(content: String): List<ChatMessage> {
        addMessage("user", content)
        return chatCompletionsUseCase.requestChatCompletions(params)
            .also { params.messages.addAll(it) }
    }

    override fun execute(content: String, callback: OpenAi.Callback<List<ChatMessage>>) {
        scope.launch {
            runCatching { execute(content) }
                .onSuccess { callback.onSuccess(it) }
                .onFailure { callback.onError(it) }
        }
    }
}
