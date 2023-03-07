package co.yml.ychat.domain.mapper

import co.yml.ychat.data.dto.ChatCompletionParamsDto
import co.yml.ychat.data.dto.ChatCompletionsDto
import co.yml.ychat.data.dto.ChatMessageDto
import co.yml.ychat.domain.model.ChatCompletionsParams
import co.yml.ychat.domain.model.ChatMessage

internal fun ChatCompletionsDto.toChatMessages(): List<ChatMessage> {
    return this.choices.map {
        ChatMessage(it.message.role, it.message.content)
    }
}

internal fun ChatCompletionsParams.toChatCompletionParamsDto(): ChatCompletionParamsDto {
    return ChatCompletionParamsDto(
        model = this.model,
        messages = this.messages.map { ChatMessageDto(it.role, it.content) },
        maxTokens = this.maxTokens,
        temperature = this.temperature,
        topP = this.topP,
        maxResults = this.maxResults,
    )
}
