package co.yml.ychatgpt.data.api.impl

import co.yml.ychatgpt.data.api.ChatGptApi
import co.yml.ychatgpt.data.dto.CompletionDto
import co.yml.ychatgpt.data.dto.CompletionParamsDto
import co.yml.ychatgpt.data.infrastructure.ApiExecutor
import co.yml.ychatgpt.data.infrastructure.ApiResult
import io.ktor.http.HttpMethod

internal class ChatGptApiImpl(private val apiExecutor: ApiExecutor) : ChatGptApi {

    override suspend fun completion(paramsDto: CompletionParamsDto): ApiResult<CompletionDto> {
        return apiExecutor
            .setEndpoint("v1/completions")
            .setHttpMethod(HttpMethod.Post)
            .setBody(paramsDto)
            .execute()
    }
}
