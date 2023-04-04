package co.yml.ychat.domain.mapper

import co.yml.ychat.data.dto.AudioParamsDto
import co.yml.ychat.domain.model.AudioParams
import co.yml.ychat.domain.model.FileBytes

internal fun AudioParams.toAudioParamsDto(filename: String, fileBytes: FileBytes): AudioParamsDto {
    return AudioParamsDto(
        filename = filename,
        byteArray = fileBytes,
        model = this.model,
        prompt = this.prompt,
        responseFormat = this.responseFormat,
        temperature = this.temperature,
        language = this.language,
    )
}
