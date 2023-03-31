package co.yml.ychat.domain.usecases

import co.yml.ychat.data.api.ChatGptApi
import co.yml.ychat.domain.mapper.toChatCompletionParamsDto
import co.yml.ychat.domain.mapper.toChatMessages
import co.yml.ychat.domain.model.ChatCompletionsParams
import co.yml.ychat.domain.model.ChatMessage

internal class ChatCompletionsUseCase(private val chatGptApi: ChatGptApi) {

    suspend fun requestChatCompletions(params: ChatCompletionsParams): List<ChatMessage> {
        val requestDto = params.toChatCompletionParamsDto()
        val response = chatGptApi.chatCompletions(requestDto)
        return response.getBodyOrThrow().toChatMessages()
    }
}
