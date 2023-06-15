package co.yml.openai.provider.domain.usecases

import co.yml.openai.provider.data.api.OpenAiApi
import co.yml.openai.provider.domain.mapper.toChatCompletionParamsDto
import co.yml.openai.provider.domain.mapper.toChatMessages
import co.yml.openai.provider.domain.model.ChatCompletionsParams
import co.yml.openai.provider.domain.model.ChatMessage

internal class ChatCompletionsUseCase(private val openAiApi: OpenAiApi) {

    suspend fun requestChatCompletions(params: ChatCompletionsParams): List<ChatMessage> {
        val requestDto = params.toChatCompletionParamsDto()
        val response = openAiApi.chatCompletions(requestDto)
        return response.getBodyOrThrow().toChatMessages()
    }
}
