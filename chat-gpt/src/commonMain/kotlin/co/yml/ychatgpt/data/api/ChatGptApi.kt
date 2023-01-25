package co.yml.ychatgpt.data.api

import co.yml.ychatgpt.data.dto.CompletionDto
import co.yml.ychatgpt.data.dto.CompletionParamsDto
import co.yml.ychatgpt.data.infrastructure.ApiResult

internal interface ChatGptApi {

    suspend fun completion(paramsDto: CompletionParamsDto): ApiResult<CompletionDto>
}