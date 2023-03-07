package co.yml.ychat.domain.usecases

import co.yml.ychat.data.api.ChatGptApi
import co.yml.ychat.data.dto.ChoiceDto
import co.yml.ychat.data.dto.CompletionDto
import co.yml.ychat.data.dto.UsageDto
import co.yml.ychat.data.exception.ChatGptException
import co.yml.ychat.data.infrastructure.ApiResult
import co.yml.ychat.data.storage.ChatLogStorage
import co.yml.ychat.domain.model.CompletionParams
import io.mockk.coEvery
import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import io.mockk.runs
import io.mockk.verify
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlinx.coroutines.runBlocking

class CompletionUseCaseTest {

    private lateinit var completionUseCase: CompletionUseCase

    private val chatGptApiMock = mockk<ChatGptApi>()

    private val chatLogStorageMock = mockk<ChatLogStorage>()

    @BeforeTest
    fun setup() {
        completionUseCase = CompletionUseCase(chatGptApiMock, chatLogStorageMock)
    }

    @Test
    fun `on completion when request not succeed then should remove last appended input and throw exception`() {
        // arrange
        val input = "Say this is a test."
        val apiResult = ApiResult<CompletionDto>(exception = ChatGptException())
        val completionParams = CompletionParams(prompt = input, enableChatStorage = true)
        every { chatLogStorageMock.buildChatInput(input) } returns input
        every { chatLogStorageMock.removeLastAppendedInput() } just runs
        coEvery { chatGptApiMock.completion(any()) } returns apiResult

        // act
        val result =
            runCatching { runBlocking { completionUseCase.completion(completionParams) } }

        // assert
        verify(exactly = 1) { chatLogStorageMock.removeLastAppendedInput() }
        assertEquals(true, result.exceptionOrNull() is ChatGptException)
    }

    @Test
    fun `on completion when request succeed then should append answer and return completion model`() {
        // arrange
        val input = "Say this is a test."
        val completionDto = buildCompletionDto("This indeed a test")
        val apiResult = ApiResult(body = completionDto)
        val completionParams = CompletionParams(prompt = input, enableChatStorage = true)
        every { chatLogStorageMock.buildChatInput(input) } returns input
        every { chatLogStorageMock.appendAnswer("This indeed a test") } just runs
        coEvery { chatGptApiMock.completion(any()) } returns apiResult

        // act
        val result = runBlocking { completionUseCase.completion(completionParams) }

        // assert
        verify(exactly = 1) { chatLogStorageMock.appendAnswer("This indeed a test") }
        assertEquals("This indeed a test", result.choices.first().text)
    }

    private fun buildCompletionDto(answer: String): CompletionDto {
        return CompletionDto(
            id = "1",
            model = "model",
            choices = listOf(
                ChoiceDto(
                    text = answer,
                    index = 1,
                    logProbs = null,
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
