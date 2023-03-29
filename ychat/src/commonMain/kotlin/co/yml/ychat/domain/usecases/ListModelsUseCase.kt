package co.yml.ychat.domain.usecases

import co.yml.ychat.data.api.ChatGptApi
import co.yml.ychat.domain.mapper.toModel
import co.yml.ychat.domain.model.AIModel

internal data class ListModelsUseCase(private val chatGptApi: ChatGptApi) {

    suspend fun getListModels(): List<AIModel> {
        val response = chatGptApi.models()
        return response.getBodyOrThrow().models.toModel()
    }
}
