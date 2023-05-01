package co.yml.ducai.provider.data.api

import co.yml.ducai.provider.data.dto.DucAiCompletionDto
import co.yml.ducai.provider.data.dto.DucAiCompletionParamsDto
import co.yml.ychat.core.network.infrastructure.ApiResult

const val BASE_URL = "duchaba-yml-text-bert.hf.space"

internal interface DucAIApi {

    suspend fun completion(paramsDto: DucAiCompletionParamsDto): ApiResult<DucAiCompletionDto>
}
