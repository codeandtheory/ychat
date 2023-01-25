package co.yml.ychatgpt.entrypoint.mapper

import co.yml.ychatgpt.data.dto.CompletionParamsDto
import co.yml.ychatgpt.entrypoint.model.CompletionParams

internal fun CompletionParams.toCompletionParamsDto(text: String): CompletionParamsDto {
    return CompletionParamsDto(
        model = this.model,
        prompt = text,
        maxTokens = this.maxTokens,
        temperature = this.temperature,
        topP = this.topP,
    )
}
