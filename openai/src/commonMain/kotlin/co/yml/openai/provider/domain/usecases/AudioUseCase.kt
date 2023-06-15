package co.yml.openai.provider.domain.usecases

import co.yml.ychat.core.model.FileBytes
import co.yml.openai.provider.data.api.OpenAiApi
import co.yml.openai.provider.domain.mapper.toAudioParamsDto
import co.yml.openai.provider.domain.model.AudioParams

internal class AudioUseCase(private val chatGptApi: OpenAiApi) {

    suspend fun requestAudioTranscription(
        filename: String,
        audioFile: FileBytes,
        params: AudioParams
    ): String {
        val requestDto = params.toAudioParamsDto(filename, audioFile)
        val response = chatGptApi.audioTranscriptions(requestDto)
        return response.getBodyOrThrow().text
    }

    suspend fun requestAudioTranslations(
        filename: String,
        audioFile: FileBytes,
        params: AudioParams
    ): String {
        val requestDto = params.toAudioParamsDto(filename, audioFile)
        val response = chatGptApi.audioTranslations(requestDto)
        return response.getBodyOrThrow().text
    }
}
