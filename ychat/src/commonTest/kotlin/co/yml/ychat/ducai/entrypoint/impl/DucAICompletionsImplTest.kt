package co.yml.ychat.ducai.entrypoint.impl

import co.yml.ychat.YChat
import co.yml.ychat.ducai.domain.model.DucAiCompletionModel
import co.yml.ychat.ducai.domain.model.DucAiCompletionParams
import co.yml.ychat.ducai.domain.usecases.CompletionDucAIUseCase
import io.mockk.Runs
import io.mockk.coEvery
import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

class DucAICompletionsImplTest {

    private val dispatcher = Dispatchers.Default
    private val completionUseCase = mockk<CompletionDucAIUseCase>()
    private val callback = mockk<YChat.Callback<String>>()

    private lateinit var ducAICompletions: DucAICompletionsImpl

    @BeforeTest
    fun setUp() {
        ducAICompletions = DucAICompletionsImpl(completionUseCase, dispatcher)
    }

    @Test
    fun `test execute with suspend function`() = runBlocking {
        val input = "test input"
        val expectedOutput = "test output"
        val ducAiCompletionParams = DucAiCompletionParams(data = input)

        coEvery { completionUseCase.completion(ducAiCompletionParams) } returns DucAiCompletionModel(
            data = expectedOutput
        )

        ducAICompletions.setInput(input)
        val result = ducAICompletions.execute()

        assertEquals(expectedOutput, result)
    }

    @Test
    fun `test execute with callback`() = runBlocking {
        val input = "test input"
        val expectedOutput = "test output"
        val ducAiCompletionParams = DucAiCompletionParams(data = input)

        coEvery { completionUseCase.completion(ducAiCompletionParams) } returns DucAiCompletionModel(
            data = expectedOutput
        )
        every { callback.onSuccess(expectedOutput) } just Runs

        ducAICompletions.setInput(input)
        ducAICompletions.execute(callback)

        verify { callback.onSuccess(expectedOutput) }
    }
}
