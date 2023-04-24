package co.yml.ychat.ducai.data.api.impl

import co.yml.ychat.ducai.data.api.DucAIApi
import co.yml.ychat.data.infrastructure.ApiExecutor
import co.yml.ychat.data.infrastructure.ApiResult
import co.yml.ychat.ducai.data.dto.DucAiCompletionDto
import co.yml.ychat.ducai.data.dto.DucAiCompletionParamsDto
import io.ktor.http.HttpMethod

internal class DucAIApiImpl(private val apiExecutor: ApiExecutor) : DucAIApi {

    override suspend fun completion(paramsDto: DucAiCompletionParamsDto): ApiResult<DucAiCompletionDto> {
        return apiExecutor
            .setEndpoint("run/predict")
            .setHttpMethod(HttpMethod.Post)
            .setBody(paramsDto)
            .execute()
    }

}
