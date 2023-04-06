package co.yml.ychat.domain.usecases

import co.yml.ychat.data.api.ChatGptApi
import co.yml.ychat.data.dto.AudioResultDto
import co.yml.ychat.data.exception.ChatGptException
import co.yml.ychat.data.infrastructure.ApiResult
import co.yml.ychat.domain.model.AudioParams
import co.yml.ychat.domain.model.FileBytes
import io.mockk.coEvery
import io.mockk.mockk
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlinx.coroutines.runBlocking

class AudioUseCaseTest {

    private lateinit var useCase: AudioUseCase

    private val chatGptApiMock = mockk<ChatGptApi>()

    @BeforeTest
    fun setup() {
        useCase = AudioUseCase(chatGptApiMock)
    }

    @Test
    fun `on requestAudioTranscription when request succeed then should return formatted result`() {
        // arrange
        val fileName = "audio-test.m4a"
        val audioFile = ByteArray(1024) as FileBytes
        val audioParams = AudioParams()
        val audioResultDto = AudioResultDto("this is a test.")
        val apiResult = ApiResult(body = audioResultDto)
        coEvery { chatGptApiMock.audioTranscriptions(any()) } returns apiResult

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
        val apiResult = ApiResult<AudioResultDto>(exception = ChatGptException())
        coEvery { chatGptApiMock.audioTranscriptions(any()) } returns apiResult

        // act
        val result = runCatching {
            runBlocking { useCase.requestAudioTranscription(fileName, audioFile, audioParams) }
        }

        // assert
        assertEquals(true, result.exceptionOrNull() is ChatGptException)
    }

    @Test
    fun `on requestAudioTranslations when request succeed then should return formatted result`() {
        // arrange
        val fileName = "audio-test.m4a"
        val audioFile = ByteArray(1024) as FileBytes
        val audioParams = AudioParams()
        val audioResultDto = AudioResultDto("this is a test.")
        val apiResult = ApiResult(body = audioResultDto)
        coEvery { chatGptApiMock.audioTranslations(any()) } returns apiResult

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
        val apiResult = ApiResult<AudioResultDto>(exception = ChatGptException())
        coEvery { chatGptApiMock.audioTranslations(any()) } returns apiResult

        // act
        val result = runCatching {
            runBlocking { useCase.requestAudioTranslations(fileName, audioFile, audioParams) }
        }

        // assert
        assertEquals(true, result.exceptionOrNull() is ChatGptException)
    }
}
