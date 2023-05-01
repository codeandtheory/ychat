package co.yml.ducai.provider.data.api.impl

import co.yml.ducai.provider.data.api.DucAIApi
import co.yml.ducai.provider.data.dto.DucAiCompletionDto
import co.yml.ducai.provider.data.dto.DucAiCompletionParamsDto
import co.yml.ychat.core.network.infrastructure.ApiExecutor
import co.yml.ychat.core.network.infrastructure.ApiResult
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
