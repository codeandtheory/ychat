package co.yml.ychat.domain.usecases

import co.yml.ychat.data.api.ChatGptApi
import co.yml.ychat.domain.mapper.toAudioParamsDto
import co.yml.ychat.domain.model.AudioParams
import co.yml.ychat.domain.model.FileBytes

internal class AudioUseCase(private val chatGptApi: ChatGptApi) {

    suspend fun requestAudioTranscription(
        filename: String,
        audioFile: FileBytes,
        params: AudioParams
    ): String {
        val requestDto = params.toAudioParamsDto(filename, audioFile)
        val response = chatGptApi.audioTranscriptions(requestDto)
        return response.getBodyOrThrow().text
    }
}
