package co.yml.ducai.provider.domain.usecases

import co.yml.ducai.provider.data.api.DucAIApi
import co.yml.ducai.provider.data.dto.DucAiCompletionDto
import co.yml.ducai.provider.domain.model.DucAiCompletionModel
import co.yml.ducai.provider.domain.model.DucAiCompletionParams
import co.yml.ychat.core.exceptions.YChatException
import co.yml.ychat.core.network.infrastructure.ApiResult
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import kotlin.test.Test
import kotlin.test.assertEquals

class CompletionDucAIUseCaseTest {
    private val ducAIApi: DucAIApi = mockk()
    private val completionDucAIUseCase = CompletionDucAIUseCase(ducAIApi)

    @Test
    fun `on completion when request succeed then should append answer and return completion model`() =
        runBlocking {
            // arrange
            val ducAiCompletionParams = DucAiCompletionParams(data = "input")
            val expectedDucAiCompletionModel = DucAiCompletionModel(data = "input")
            coEvery { ducAIApi.completion(any()) } returns ApiResult(
                DucAiCompletionDto(
                    data = listOf(
                        "input"
                    )
                )
            )

            // act
            val result = completionDucAIUseCase.completion(ducAiCompletionParams)

            // assert
            assertEquals(expectedDucAiCompletionModel, result)
            coVerify { ducAIApi.completion(any()) }
        }

    @Test
    fun `on completion when request not succeed then should remove last appended input and throw exception`() =
        runBlocking {
            // arrange
            val apiResult = ApiResult<DucAiCompletionDto>(exception = YChatException())
            val ducAiCompletionParams = DucAiCompletionParams(data = "input")
            coEvery { ducAIApi.completion(any()) } returns apiResult

            // act
            val result =
                runCatching { runBlocking { completionDucAIUseCase.completion(ducAiCompletionParams) } }

            // assert
            assertEquals(true, result.exceptionOrNull() is YChatException)
            coVerify { ducAIApi.completion(any()) }
        }
}
