package co.yml.openai.provider.domain.usecases

import co.yml.openai.provider.data.api.OpenAiApi
import co.yml.openai.provider.domain.mapper.toModel
import co.yml.openai.provider.domain.model.AIModel

internal class ListModelsUseCase(private val openAiApi: OpenAiApi) {

    suspend fun getListModels(): List<AIModel> {
        val response = openAiApi.models()
        return response.getBodyOrThrow().models.toModel()
    }
}
