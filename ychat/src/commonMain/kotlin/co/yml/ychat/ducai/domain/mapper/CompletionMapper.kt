package co.yml.ychat.ducai.domain.mapper

import co.yml.ychat.ducai.data.dto.DucAiCompletionDto
import co.yml.ychat.ducai.data.dto.DucAiCompletionParamsDto
import co.yml.ychat.ducai.domain.model.DucAiCompletionModel
import co.yml.ychat.ducai.domain.model.DucAiCompletionParams

internal fun DucAiCompletionDto.toCompletionModel() = DucAiCompletionModel(
    data = this.data.joinToString(" ")
)

internal fun DucAiCompletionParams.toCompletionParamsDto() = DucAiCompletionParamsDto(
    data = listOf(this.data)
)
