package co.yml.ychat.ducai.domain.mapper

import co.yml.ychat.ducai.data.dto.CompletionDto
import co.yml.ychat.ducai.data.dto.CompletionParamsDto
import co.yml.ychat.ducai.domain.model.CompletionModel
import co.yml.ychat.ducai.domain.model.CompletionParams

internal fun CompletionDto.toCompletionModel(): CompletionModel {
    return CompletionModel(
        data = this.data
    )
}

internal fun CompletionParams.toCompletionParamsDto(): CompletionParamsDto {
    return CompletionParamsDto(
        data = this.data
    )
}
