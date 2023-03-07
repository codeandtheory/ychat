package co.yml.ychat.entrypoint.impl

import co.yml.ychat.YChat
import co.yml.ychat.domain.model.ChatCompletionsParams
import co.yml.ychat.domain.model.ChatMessage
import co.yml.ychat.domain.usecases.ChatCompletionsUseCase
import co.yml.ychat.entrypoint.features.ChatCompletions
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

internal class ChatCompletionsImpl(
    private val dispatcher: CoroutineDispatcher,
    private val chatCompletionsUseCase: ChatCompletionsUseCase,
) : ChatCompletions {

    private val scope by lazy { CoroutineScope(SupervisorJob() + dispatcher) }

    private var params: ChatCompletionsParams = ChatCompletionsParams()

    override fun setModel(model: String): ChatCompletions {
        params.model = model
        return this
    }

    override fun setTopP(topP: Double): ChatCompletions {
        params.topP = topP
        return this
    }

    override fun setTemperature(temperature: Double): ChatCompletions {
        params.temperature = temperature
        return this
    }

    override fun setMaxResults(results: Int): ChatCompletions {
        params.maxResults = results
        return this
    }

    override fun setMaxTokens(tokens: Int): ChatCompletions {
        params.maxTokens = tokens
        return this
    }

    override fun addMessage(role: String, content: String): ChatCompletions {
        params.messages.add(ChatMessage(role, content))
        return this
    }

    override suspend fun execute(content: String): List<ChatMessage> {
        addMessage("user", content)
        return chatCompletionsUseCase.requestChatCompletions(params)
            .also { params.messages.addAll(it) }
    }

    override fun execute(content: String, callback: YChat.Callback<List<ChatMessage>>) {
        scope.launch {
            runCatching { execute(content) }
                .onSuccess { callback.onSuccess(it) }
                .onFailure { callback.onError(it) }
        }
    }
}
