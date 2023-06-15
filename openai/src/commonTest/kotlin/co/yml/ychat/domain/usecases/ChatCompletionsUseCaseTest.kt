package co.yml.ychat.domain.usecases

import co.yml.openai.provider.data.api.OpenAiApi
import co.yml.openai.provider.data.dto.ChatCompletionsChoiceDto
import co.yml.openai.provider.data.dto.ChatCompletionsDto
import co.yml.openai.provider.data.dto.ChatMessageDto
import co.yml.openai.provider.data.dto.UsageDto
import co.yml.openai.provider.domain.model.ChatCompletionsParams
import co.yml.openai.provider.domain.model.ChatMessage
import co.yml.openai.provider.domain.usecases.ChatCompletionsUseCase
import co.yml.ychat.core.exceptions.YChatException
import co.yml.ychat.core.network.infrastructure.ApiResult
import io.mockk.coEvery
import io.mockk.mockk
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlinx.coroutines.runBlocking

class ChatCompletionsUseCaseTest {

    private lateinit var chatCompletionsUseCase: ChatCompletionsUseCase

    private val openAiApiMock = mockk<OpenAiApi>()

    @BeforeTest
    fun setup() {
        chatCompletionsUseCase = ChatCompletionsUseCase(openAiApiMock)
    }

    @Test
    fun `on requestChatCompletions when request succeed then should return formatted result`() {
        // arrange
        val messages = arrayListOf(ChatMessage("user", "Say this is a test."))
        val chatCompletionDto = buildChatCompletionsDto("This indeed a test")
        val params = ChatCompletionsParams(messages)
        val apiResult = ApiResult(body = chatCompletionDto)
        coEvery { openAiApiMock.chatCompletions(any()) } returns apiResult

        // act
        val result = runBlocking { chatCompletionsUseCase.requestChatCompletions(params) }

        // assert
        assertEquals("This indeed a test", result.last().content)
    }

    @Test
    fun `on requestChatCompletions when not request succeed then should throw an exception`() {
        // arrange
        val messages = arrayListOf(ChatMessage("user", "Say this is a test."))
        val params = ChatCompletionsParams(messages)
        val apiResult = ApiResult<ChatCompletionsDto>(exception = YChatException())
        coEvery { openAiApiMock.chatCompletions(any()) } returns apiResult

        // act
        val result =
            runCatching { runBlocking { chatCompletionsUseCase.requestChatCompletions(params) } }

        // assert
        assertEquals(true, result.exceptionOrNull() is YChatException)
    }

    private fun buildChatCompletionsDto(answer: String): ChatCompletionsDto {
        return ChatCompletionsDto(
            id = "1",
            model = "gpt",
            choices = listOf(
                ChatCompletionsChoiceDto(
                    index = 0,
                    message = ChatMessageDto("assistance", answer),
                    finishReason = ""
                )
            ),
            usage = UsageDto(
                promptToken = 1,
                completionTokens = 1,
                totalTokens = 1
            )
        )
    }
}
