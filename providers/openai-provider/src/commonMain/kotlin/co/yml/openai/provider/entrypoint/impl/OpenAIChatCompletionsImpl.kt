package co.yml.openai.provider.entrypoint.impl

import co.yml.openai.provider.OpenAI
import co.yml.openai.provider.entrypoint.features.OpenAIChatCompletions
import co.yml.openai.provider.domain.model.ChatCompletionsParams
import co.yml.openai.provider.domain.model.ChatMessage
import co.yml.openai.provider.domain.usecases.ChatCompletionsUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

internal class OpenAIChatCompletionsImpl(
    private val dispatcher: CoroutineDispatcher,
    private val chatCompletionsUseCase: ChatCompletionsUseCase,
) : OpenAIChatCompletions {

    private val scope by lazy { CoroutineScope(SupervisorJob() + dispatcher) }

    private var params: ChatCompletionsParams = ChatCompletionsParams()

    override fun setModel(model: String): OpenAIChatCompletions {
        params.model = model
        return this
    }

    override fun setTopP(topP: Double): OpenAIChatCompletions {
        params.topP = topP
        return this
    }

    override fun setTemperature(temperature: Double): OpenAIChatCompletions {
        params.temperature = temperature
        return this
    }

    override fun setMaxResults(results: Int): OpenAIChatCompletions {
        params.maxResults = results
        return this
    }

    override fun setMaxTokens(tokens: Int): OpenAIChatCompletions {
        params.maxTokens = tokens
        return this
    }

    override fun addMessage(role: String, content: String): OpenAIChatCompletions {
        params.messages.add(ChatMessage(role, content))
        return this
    }

    override suspend fun execute(content: String): List<ChatMessage> {
        addMessage("user", content)
        return chatCompletionsUseCase.requestChatCompletions(params)
            .also { params.messages.addAll(it) }
    }

    override fun execute(content: String, callback: OpenAI.Callback<List<ChatMessage>>) {
        scope.launch {
            runCatching { execute(content) }
                .onSuccess { callback.onSuccess(it) }
                .onFailure { callback.onError(it) }
        }
    }
}
