package co.yml.ychat.ducai.domain.usecases

import co.yml.ychat.data.exception.ChatGptException
import co.yml.ychat.data.infrastructure.ApiResult
import co.yml.ychat.ducai.data.api.DucAIApi
import co.yml.ychat.ducai.data.dto.CompletionDto
import co.yml.ychat.ducai.domain.model.CompletionModel
import co.yml.ychat.ducai.domain.model.CompletionParams
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
            val completionParams = CompletionParams(data = "input")
            val expectedCompletionModel = CompletionModel(data = "input")
            coEvery { ducAIApi.completion(any()) } returns ApiResult(CompletionDto(data = "input"))


            // act
            val result = completionDucAIUseCase.completion(completionParams)

            // assert
            assertEquals(expectedCompletionModel, result)
            coVerify { ducAIApi.completion(any()) }

        }

    @Test
    fun `on completion when request not succeed then should remove last appended input and throw exception`() =
        runBlocking {
            // arrange
            val apiResult = ApiResult<CompletionDto>(exception = ChatGptException())
            val completionParams = CompletionParams(data = "input")
            coEvery { ducAIApi.completion(any()) } returns apiResult

            // act
            val result =
                runCatching { runBlocking { completionDucAIUseCase.completion(completionParams) } }

            // assert
            assertEquals(true, result.exceptionOrNull() is ChatGptException)
            coVerify { ducAIApi.completion(any()) }
        }

}