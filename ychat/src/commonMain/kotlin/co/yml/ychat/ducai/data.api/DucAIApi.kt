package co.yml.ychat.ducai.data.api


import co.yml.ychat.ducai.data.dto.CompletionDto
import co.yml.ychat.ducai.data.dto.CompletionParamsDto
import co.yml.ychat.data.infrastructure.ApiResult

const val BASE_URL = "https://duchaba-yml-text-bert.hf.space/"

internal interface DucAIApi {

    suspend fun completion(paramsDto: CompletionParamsDto): ApiResult<CompletionDto>

}
