package co.yml.ychat.ducai.domain.usecases

import co.yml.ychat.data.infrastructure.ApiResult
import co.yml.ychat.ducai.data.api.DucAIApi
import co.yml.ychat.ducai.data.dto.CompletionDto
import co.yml.ychat.ducai.domain.mapper.toCompletionModel
import co.yml.ychat.ducai.domain.mapper.toCompletionParamsDto
import co.yml.ychat.ducai.domain.model.CompletionModel
import co.yml.ychat.ducai.domain.model.CompletionParams

internal class CompletionDucAIUseCase(
    val ducAIApi: DucAIApi
) {

    suspend fun completion(completionParams: CompletionParams): CompletionModel {
        return requestCompletion(completionParams).getBodyOrThrow().toCompletionModel()
    }

    private suspend fun requestCompletion(
        completionParams: CompletionParams
    ): ApiResult<CompletionDto> {
        val completionDto = completionParams.toCompletionParamsDto()
        return ducAIApi.completion(completionDto)
    }

}
