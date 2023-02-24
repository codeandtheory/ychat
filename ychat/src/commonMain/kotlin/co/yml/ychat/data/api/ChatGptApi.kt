package co.yml.ychat.data.api

import co.yml.ychat.data.dto.CompletionDto
import co.yml.ychat.data.dto.CompletionParamsDto
import co.yml.ychat.data.infrastructure.ApiResult

internal interface ChatGptApi {

    suspend fun completion(paramsDto: CompletionParamsDto): ApiResult<CompletionDto>
}
