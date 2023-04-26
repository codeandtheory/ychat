package co.yml.ychat.ducai.data.api

import co.yml.ychat.data.infrastructure.ApiResult
import co.yml.ychat.ducai.data.dto.DucAiCompletionDto
import co.yml.ychat.ducai.data.dto.DucAiCompletionParamsDto

const val BASE_URL = "duchaba-yml-text-bert.hf.space"

internal interface DucAIApi {

    suspend fun completion(paramsDto: DucAiCompletionParamsDto): ApiResult<DucAiCompletionDto>
}
