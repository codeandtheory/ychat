package co.yml.ychat.ducai.data.api


import co.yml.ychat.ducai.data.dto.DucAiCompletionDto
import co.yml.ychat.ducai.data.dto.DucAiCompletionParamsDto
import co.yml.ychat.data.infrastructure.ApiResult

const val BASE_URL = "https://duchaba-yml-text-bert.hf.space/"

internal interface DucAIApi {

    suspend fun completion(paramsDto: DucAiCompletionParamsDto): ApiResult<DucAiCompletionDto>

}
