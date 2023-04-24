package co.yml.ychat.ducai.domain.usecases

import co.yml.ychat.data.infrastructure.ApiResult
import co.yml.ychat.ducai.data.api.DucAIApi
import co.yml.ychat.ducai.data.dto.DucAiCompletionDto
import co.yml.ychat.ducai.domain.mapper.toCompletionModel
import co.yml.ychat.ducai.domain.mapper.toCompletionParamsDto
import co.yml.ychat.ducai.domain.model.DucAiCompletionModel
import co.yml.ychat.ducai.domain.model.DucAiCompletionParams

internal class CompletionDucAIUseCase(
    private val ducAIApi: DucAIApi
) {

    suspend fun completion(ducAiCompletionParams: DucAiCompletionParams): DucAiCompletionModel {
        return requestCompletion(ducAiCompletionParams).getBodyOrThrow().toCompletionModel()
    }

    private suspend fun requestCompletion(
        ducAiCompletionParams: DucAiCompletionParams
    ): ApiResult<DucAiCompletionDto> {
        val completionDto = ducAiCompletionParams.toCompletionParamsDto()
        return ducAIApi.completion(completionDto)
    }

}
