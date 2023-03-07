package co.yml.ychat.data.api

import co.yml.ychat.data.dto.ChatCompletionParamsDto
import co.yml.ychat.data.dto.ChatCompletionsDto
import co.yml.ychat.data.dto.CompletionDto
import co.yml.ychat.data.dto.CompletionParamsDto
import co.yml.ychat.data.infrastructure.ApiResult

internal interface ChatGptApi {

    suspend fun completion(paramsDto: CompletionParamsDto): ApiResult<CompletionDto>

    suspend fun chatCompletions(paramsDto: ChatCompletionParamsDto): ApiResult<ChatCompletionsDto>
}
