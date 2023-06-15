package co.yml.ychat.domain.usecases

import co.yml.openai.provider.data.api.OpenAiApi
import co.yml.openai.provider.data.dto.ModelDto
import co.yml.openai.provider.data.dto.ModelListDto
import co.yml.openai.provider.domain.usecases.ListModelsUseCase
import co.yml.ychat.core.exceptions.YChatException
import co.yml.ychat.core.network.infrastructure.ApiResult
import io.mockk.coEvery
import io.mockk.mockk
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlinx.coroutines.runBlocking

class ListModelsUseCaseTest {

    private lateinit var useCase: ListModelsUseCase

    private val apiMock = mockk<OpenAiApi>()

    @BeforeTest
    fun setup() {
        useCase = ListModelsUseCase(apiMock)
    }

    @Test
    fun `on getListModels when request succeed then should return formatted result`() {
        // arrange
        val expectedIds = listOf("model1", "model2")
        val modelListDto = buildModelListDto(expectedIds)
        val apiResult = ApiResult(body = modelListDto)
        coEvery { apiMock.models() } returns apiResult

        // act
        val result = runBlocking { useCase.getListModels() }

        // assert
        assertEquals("model1", result[0].id)
        assertEquals("model2", result[1].id)
    }

    @Test
    fun `on getListModels when not request succeed then should throw an exception`() {
        // arrange
        val apiResult = ApiResult<ModelListDto>(exception = YChatException())
        coEvery { apiMock.models() } returns apiResult

        // act
        val result = runCatching { runBlocking { useCase.getListModels() } }

        // assert
        assertEquals(true, result.exceptionOrNull() is YChatException)
    }

    private fun buildModelListDto(modelIds: List<String>): ModelListDto {
        return ModelListDto(modelIds.map { ModelDto(id = it, ownedBy = "", emptyList()) })
    }
}
