package co.yml.ychat.domain.usecases

import co.yml.openai.provider.data.api.OpenAiApi
import co.yml.openai.provider.data.dto.AudioResultDto
import co.yml.openai.provider.domain.model.AudioParams
import co.yml.openai.provider.domain.usecases.AudioUseCase
import co.yml.ychat.core.exceptions.YChatException
import co.yml.ychat.core.model.FileBytes
import co.yml.ychat.core.network.infrastructure.ApiResult
import io.mockk.coEvery
import io.mockk.mockk
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlinx.coroutines.runBlocking

class AudioUseCaseTest {

    private lateinit var useCase: AudioUseCase

    private val openAiApiMock = mockk<OpenAiApi>()

    @BeforeTest
    fun setup() {
        useCase = AudioUseCase(openAiApiMock)
    }

    @Test
    fun `on requestAudioTranscription when request succeed then should return formatted result`() {
        // arrange
        val fileName = "audio-test.m4a"
        val audioFile = ByteArray(1024) as FileBytes
        val audioParams = AudioParams()
        val audioResultDto = AudioResultDto("this is a test.")
        val apiResult = ApiResult(body = audioResultDto)
        coEvery { openAiApiMock.audioTranscriptions(any()) } returns apiResult

        // act
        val result = runBlocking { useCase.requestAudioTranscription(fileName, audioFile, audioParams) }

        // assert
        assertEquals("this is a test.", result)
    }

    @Test
    fun `on requestAudioTranscription when not request succeed then should throw an exception`() {
        // arrange
        val fileName = "audio-test.m4a"
        val audioFile = ByteArray(1024) as FileBytes
        val audioParams = AudioParams()
        val apiResult = ApiResult<AudioResultDto>(exception = YChatException())
        coEvery { openAiApiMock.audioTranscriptions(any()) } returns apiResult

        // act
        val result = runCatching {
            runBlocking { useCase.requestAudioTranscription(fileName, audioFile, audioParams) }
        }

        // assert
        assertEquals(true, result.exceptionOrNull() is YChatException)
    }

    @Test
    fun `on requestAudioTranslations when request succeed then should return formatted result`() {
        // arrange
        val fileName = "audio-test.m4a"
        val audioFile = ByteArray(1024) as FileBytes
        val audioParams = AudioParams()
        val audioResultDto = AudioResultDto("this is a test.")
        val apiResult = ApiResult(body = audioResultDto)
        coEvery { openAiApiMock.audioTranslations(any()) } returns apiResult

        // act
        val result = runBlocking { useCase.requestAudioTranslations(fileName, audioFile, audioParams) }

        // assert
        assertEquals("this is a test.", result)
    }

    @Test
    fun `on requestAudioTranslations when not request succeed then should throw an exception`() {
        // arrange
        val fileName = "audio-test.m4a"
        val audioFile = ByteArray(1024) as FileBytes
        val audioParams = AudioParams()
        val apiResult = ApiResult<AudioResultDto>(exception = YChatException())
        coEvery { openAiApiMock.audioTranslations(any()) } returns apiResult

        // act
        val result = runCatching {
            runBlocking { useCase.requestAudioTranslations(fileName, audioFile, audioParams) }
        }

        // assert
        assertEquals(true, result.exceptionOrNull() is YChatException)
    }
}
