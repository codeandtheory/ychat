package co.yml.ychat.ducai.domain.usecases

import co.yml.ychat.ducai.data.api.DucAIApi
import co.yml.ychat.ducai.domain.mapper.toCompletionModel
import co.yml.ychat.ducai.domain.mapper.toCompletionParamsDto
import co.yml.ychat.ducai.domain.model.DucAiCompletionParams

internal class CompletionDucAIUseCase(
    private val ducAIApi: DucAIApi
) {

    suspend fun completion(ducAiCompletionParams: DucAiCompletionParams) =
        requestCompletion(ducAiCompletionParams).getBodyOrThrow().toCompletionModel()

    private suspend fun requestCompletion(
        ducAiCompletionParams: DucAiCompletionParams
    ) = ducAIApi.completion(ducAiCompletionParams.toCompletionParamsDto())
}
