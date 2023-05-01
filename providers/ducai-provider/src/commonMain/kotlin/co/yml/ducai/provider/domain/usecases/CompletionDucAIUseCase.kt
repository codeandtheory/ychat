package co.yml.ducai.provider.domain.usecases

import co.yml.ducai.provider.data.api.DucAIApi
import co.yml.ducai.provider.domain.mapper.toCompletionModel
import co.yml.ducai.provider.domain.mapper.toCompletionParamsDto
import co.yml.ducai.provider.domain.model.DucAiCompletionParams

internal class CompletionDucAIUseCase(
    private val ducAIApi: DucAIApi
) {

    suspend fun completion(ducAiCompletionParams: DucAiCompletionParams) =
        requestCompletion(ducAiCompletionParams).getBodyOrThrow().toCompletionModel()

    private suspend fun requestCompletion(
        ducAiCompletionParams: DucAiCompletionParams
    ) = ducAIApi.completion(ducAiCompletionParams.toCompletionParamsDto())
}
