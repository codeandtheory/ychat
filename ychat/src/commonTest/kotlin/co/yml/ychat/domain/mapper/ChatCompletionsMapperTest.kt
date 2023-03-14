package co.yml.ychat.domain.mapper

import co.yml.ychat.data.dto.ChatCompletionsChoiceDto
import co.yml.ychat.data.dto.ChatCompletionsDto
import co.yml.ychat.data.dto.ChatMessageDto
import co.yml.ychat.data.dto.UsageDto
import co.yml.ychat.domain.model.ChatCompletionsParams
import co.yml.ychat.domain.model.ChatMessage
import kotlin.test.Test
import kotlin.test.assertEquals

class ChatCompletionsMapperTest {

    @Test
    fun `on convert ChatCompletionsDto to ChatMessages`() {
        val listOfChatMessages = listOf(ChatMessage("user", "message 1"), ChatMessage("user", "message 2"))
        val chatCompletionsDto = ChatCompletionsDto(
            choices = listOf(
                ChatCompletionsChoiceDto(1, ChatMessageDto("user", "message 1"), null),
                ChatCompletionsChoiceDto(1, ChatMessageDto("user", "message 2"), null)
            ),
            id = "1",
            model = "",
            usage = UsageDto(1, 1, 1)
        )
        assertEquals(listOfChatMessages, chatCompletionsDto.toChatMessages())
    }

    @Test
    fun `on convert ChatCompletionsParams to ChatCompletionParamsDto`() {
        val messages = arrayListOf(ChatMessage("user", "message 1"), ChatMessage("user", "message 2"))
        val chatCompletionsParams = ChatCompletionsParams(messages)
        assertEquals(messages.map { ChatMessageDto(it.role, it.content) }, chatCompletionsParams.toChatCompletionParamsDto().messages, "")
        assertEquals("gpt-3.5-turbo", chatCompletionsParams.toChatCompletionParamsDto().model)
        assertEquals(1, chatCompletionsParams.toChatCompletionParamsDto().maxResults)
        assertEquals(4096, chatCompletionsParams.toChatCompletionParamsDto().maxTokens)
        assertEquals(1.0, chatCompletionsParams.toChatCompletionParamsDto().temperature)
        assertEquals(1.0, chatCompletionsParams.toChatCompletionParamsDto().topP)
    }
}
