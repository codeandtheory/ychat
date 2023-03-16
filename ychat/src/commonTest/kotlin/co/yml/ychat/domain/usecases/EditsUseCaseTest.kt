package co.yml.ychat.domain.usecases

import co.yml.ychat.data.api.ChatGptApi
import co.yml.ychat.data.dto.EditsChoiceDto
import co.yml.ychat.data.dto.EditsDto
import co.yml.ychat.data.dto.UsageDto
import co.yml.ychat.data.exception.ChatGptException
import co.yml.ychat.data.infrastructure.ApiResult
import co.yml.ychat.domain.model.EditsParams
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

class EditsUseCaseTest {

    private lateinit var editsUseCase: EditsUseCase

    private val chatGptApiMock = mockk<ChatGptApi>()

    @BeforeTest
    fun setup() {
        editsUseCase = EditsUseCase(chatGptApiMock)
    }

    @Test
    fun `on requestEdits when request succeed then should return formatted result`() {
        // arrange
        val prompt = "write text"
        val editsDto = buildEditsDto(listOf<String>("text 1", "text 2"))
        val params = EditsParams(input = prompt, results = 2)
        val apiResult = ApiResult(body = editsDto)
        coEvery { chatGptApiMock.edits(any()) } returns apiResult

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
        val apiResult = ApiResult<EditsDto>(exception = ChatGptException())
        coEvery { chatGptApiMock.edits(any()) } returns apiResult

        // act
        val result =
            runCatching { runBlocking { editsUseCase.requestEdits(params) } }

        // assert
        assertEquals(true, result.exceptionOrNull() is ChatGptException)
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
