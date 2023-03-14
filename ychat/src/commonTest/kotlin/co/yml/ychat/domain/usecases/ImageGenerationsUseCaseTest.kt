package co.yml.ychat.domain.usecases

import co.yml.ychat.data.api.ChatGptApi
import co.yml.ychat.data.dto.ImageGenerationsDto
import co.yml.ychat.data.exception.ChatGptException
import co.yml.ychat.data.infrastructure.ApiResult
import co.yml.ychat.domain.model.ImageGeneratedDto
import co.yml.ychat.domain.model.ImageGenerationsParams
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

class ImageGenerationsUseCaseTest {

    private lateinit var imageGenerationsUseCase: ImageGenerationsUseCase

    private val chatGptApiMock = mockk<ChatGptApi>()

    @BeforeTest
    fun setup() {
        imageGenerationsUseCase = ImageGenerationsUseCase(chatGptApiMock)
    }

    @Test
    fun `on requestImageGenerations when request succeed then should return formatted result`() {
        // arrange
        val prompt = "/image test"
        val imageGenerationsDto = buildImageGenerationsDto("https://image-generated.test")
        val params = ImageGenerationsParams(prompt = prompt)
        val apiResult = ApiResult(body = imageGenerationsDto)
        coEvery { chatGptApiMock.imageGenerations(any()) } returns apiResult

        // act
        val result = runBlocking { imageGenerationsUseCase.requestImageGenerations(params) }

        // assert
        assertEquals("https://image-generated.test", result.last().url)
    }

    @Test
    fun `on requestChatCompletions when not request succeed then should throw an exception`() {
        // arrange
        val prompt = "/image test"
        val params = ImageGenerationsParams(prompt = prompt)
        val apiResult = ApiResult<ImageGenerationsDto>(exception = ChatGptException())
        coEvery { chatGptApiMock.imageGenerations(any()) } returns apiResult

        // act
        val result =
            runCatching { runBlocking { imageGenerationsUseCase.requestImageGenerations(params) } }

        // assert
        assertEquals(true, result.exceptionOrNull() is ChatGptException)
    }

    private fun buildImageGenerationsDto(url: String): ImageGenerationsDto {
        return ImageGenerationsDto(
            created = 12345,
            data = listOf(ImageGeneratedDto(url))
        )
    }
}
