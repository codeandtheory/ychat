package co.yml.ducai.provider.domain.mapper

import co.yml.ducai.provider.data.dto.DucAiCompletionDto
import co.yml.ducai.provider.data.dto.DucAiCompletionParamsDto
import co.yml.ducai.provider.domain.model.DucAiCompletionModel
import co.yml.ducai.provider.domain.model.DucAiCompletionParams

internal fun DucAiCompletionDto.toCompletionModel() = DucAiCompletionModel(
    data = this.data.joinToString(" ")
)

internal fun DucAiCompletionParams.toCompletionParamsDto() = DucAiCompletionParamsDto(
    data = listOf(this.data)
)
