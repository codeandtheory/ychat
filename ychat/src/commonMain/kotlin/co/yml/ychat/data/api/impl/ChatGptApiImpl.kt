package co.yml.ychat.data.api.impl

import co.yml.ychat.data.api.ChatGptApi
import co.yml.ychat.data.dto.CompletionDto
import co.yml.ychat.data.dto.CompletionParamsDto
import co.yml.ychat.data.infrastructure.ApiExecutor
import co.yml.ychat.data.infrastructure.ApiResult
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
