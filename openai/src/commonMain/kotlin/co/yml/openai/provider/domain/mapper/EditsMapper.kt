package co.yml.openai.provider.domain.mapper

import co.yml.openai.provider.data.dto.EditsDto
import co.yml.openai.provider.data.dto.EditsParamsDto
import co.yml.openai.provider.domain.model.EditsParams

internal fun EditsDto.toEditsModel(): List<String> {
    return this.choices.map { it.text }
}

internal fun EditsParams.toEditsParamsDto(): EditsParamsDto {
    return EditsParamsDto(
        model = this.model,
        input = this.input,
        instruction = this.instruction,
        results = this.results,
        temperature = this.temperature,
        topP = this.topP
    )
}
