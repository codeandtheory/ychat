package co.yml.ychat.ducai.domain.mapper

import co.yml.ychat.ducai.data.dto.DucAiCompletionDto
import co.yml.ychat.ducai.data.dto.DucAiCompletionParamsDto
import co.yml.ychat.ducai.domain.model.DucAiCompletionModel
import co.yml.ychat.ducai.domain.model.DucAiCompletionParams

internal fun DucAiCompletionDto.toCompletionModel(): DucAiCompletionModel {
    return DucAiCompletionModel(
        data = this.data
    )
}

internal fun DucAiCompletionParams.toCompletionParamsDto(): DucAiCompletionParamsDto {
    return DucAiCompletionParamsDto(
        data = this.data
    )
}
