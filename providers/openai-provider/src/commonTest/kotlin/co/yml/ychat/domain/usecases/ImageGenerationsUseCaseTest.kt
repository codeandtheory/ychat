package co.yml.ychat.domain.usecases

import co.yml.openai.provider.data.api.OpenAiApi
import co.yml.openai.provider.data.dto.ImageGeneratedDto
import co.yml.openai.provider.data.dto.ImageGenerationsDto
import co.yml.openai.provider.domain.model.ImageGenerationsParams
import co.yml.openai.provider.domain.usecases.ImageGenerationsUseCase
import co.yml.ychat.core.exceptions.YChatException
import co.yml.ychat.core.network.infrastructure.ApiResult
import io.mockk.coEvery
import io.mockk.mockk
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlinx.coroutines.runBlocking

class ImageGenerationsUseCaseTest {

    private lateinit var imageGenerationsUseCase: ImageGenerationsUseCase

    private val apiMock = mockk<OpenAiApi>()

    @BeforeTest
    fun setup() {
        imageGenerationsUseCase = ImageGenerationsUseCase(apiMock)
    }

    @Test
    fun `on requestImageGenerations when request succeed then should return formatted result`() {
        // arrange
        val prompt = "/image test"
        val imageGenerationsDto = buildImageGenerationsDto("https://image-generated.test")
        val params = ImageGenerationsParams(prompt = prompt)
        val apiResult = ApiResult(body = imageGenerationsDto)
        coEvery { apiMock.imageGenerations(any()) } returns apiResult

        // act
        val result = runBlocking { imageGenerationsUseCase.requestImageGenerations(params) }

        // assert
        assertEquals("https://image-generated.test", result.last())
    }

    @Test
    fun `on requestChatCompletions when not request succeed then should throw an exception`() {
        // arrange
        val prompt = "/image test"
        val params = ImageGenerationsParams(prompt = prompt)
        val apiResult = ApiResult<ImageGenerationsDto>(exception = YChatException())
        coEvery { apiMock.imageGenerations(any()) } returns apiResult

        // act
        val result =
            runCatching { runBlocking { imageGenerationsUseCase.requestImageGenerations(params) } }

        // assert
        assertEquals(true, result.exceptionOrNull() is YChatException)
    }

    private fun buildImageGenerationsDto(url: String): ImageGenerationsDto {
        return ImageGenerationsDto(
            created = 12345,
            data = listOf(ImageGeneratedDto(url))
        )
    }
}
