package co.yml.ychat.domain.usecases

import co.yml.ychat.data.api.ChatGptApi
import co.yml.ychat.domain.mapper.toModel
import co.yml.ychat.domain.model.AIModel

internal data class RetrieveModelUseCase(private val chatGptApi: ChatGptApi) {

    suspend fun getModel(id: String): AIModel {
        val response = chatGptApi.model(id)
        return response.getBodyOrThrow().toModel()
    }
}
