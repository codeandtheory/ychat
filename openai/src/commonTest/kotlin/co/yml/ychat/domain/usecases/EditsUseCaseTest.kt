package co.yml.ychat.domain.usecases

import co.yml.openai.provider.data.api.OpenAiApi
import co.yml.openai.provider.data.dto.EditsChoiceDto
import co.yml.openai.provider.data.dto.EditsDto
import co.yml.openai.provider.data.dto.UsageDto
import co.yml.openai.provider.domain.model.EditsParams
import co.yml.openai.provider.domain.usecases.EditsUseCase
import co.yml.ychat.core.exceptions.YChatException
import co.yml.ychat.core.network.infrastructure.ApiResult
import io.mockk.coEvery
import io.mockk.mockk
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlinx.coroutines.runBlocking

class EditsUseCaseTest {

    private lateinit var editsUseCase: EditsUseCase

    private val apiMock = mockk<OpenAiApi>()

    @BeforeTest
    fun setup() {
        editsUseCase = EditsUseCase(apiMock)
    }

    @Test
    fun `on requestEdits when request succeed then should return formatted result`() {
        // arrange
        val prompt = "write text"
        val editsDto = buildEditsDto(listOf<String>("text 1", "text 2"))
        val params = EditsParams(input = prompt, results = 2)
        val apiResult = ApiResult(body = editsDto)
        coEvery { apiMock.edits(any()) } returns apiResult

        // act
        val result = runBlocking { editsUseCase.requestEdits(params) }

        // assert
        assertEquals("text 2", result.last())
    }

    @Test
    fun `on requestEdits when not request succeed then should throw an exception`() {
        // arrange
        val prompt = "text"
        val params = EditsParams(input = prompt)
        val apiResult = ApiResult<EditsDto>(exception = YChatException())
        coEvery { apiMock.edits(any()) } returns apiResult

        // act
        val result =
            runCatching { runBlocking { editsUseCase.requestEdits(params) } }

        // assert
        assertEquals(true, result.exceptionOrNull() is YChatException)
    }

    private fun buildEditsDto(texts: List<String>): EditsDto {
        return EditsDto(
            created = 12345,
            objectType = "edits",
            choices = listOf(EditsChoiceDto(text = texts[0], index = 1), EditsChoiceDto(text = texts[1], index = 2)),
            usage = UsageDto(1, 1, 1)
        )
    }
}
