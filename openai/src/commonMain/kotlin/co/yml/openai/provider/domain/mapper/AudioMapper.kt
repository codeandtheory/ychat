package co.yml.openai.provider.domain.mapper

import co.yml.ychat.core.model.FileBytes
import co.yml.openai.provider.data.dto.AudioParamsDto
import co.yml.openai.provider.domain.model.AudioParams

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
