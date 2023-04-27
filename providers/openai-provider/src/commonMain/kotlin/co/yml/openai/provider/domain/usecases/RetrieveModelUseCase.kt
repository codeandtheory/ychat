package co.yml.openai.provider.domain.usecases

import co.yml.openai.provider.data.api.OpenAiApi
import co.yml.openai.provider.domain.mapper.toModel
import co.yml.openai.provider.domain.model.AIModel

internal class RetrieveModelUseCase(private val chatGptApi: OpenAiApi) {

    suspend fun getModel(id: String): AIModel {
        val response = chatGptApi.model(id)
        return response.getBodyOrThrow().toModel()
    }
}
