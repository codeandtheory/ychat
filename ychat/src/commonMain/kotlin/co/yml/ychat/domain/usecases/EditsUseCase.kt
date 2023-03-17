package co.yml.ychat.domain.usecases

import co.yml.ychat.data.api.ChatGptApi
import co.yml.ychat.domain.mapper.toEditsModel
import co.yml.ychat.domain.mapper.toEditsParamsDto
import co.yml.ychat.domain.model.EditsParams

internal data class EditsUseCase(private val chatGptApi: ChatGptApi) {

    suspend fun requestEdits(params: EditsParams): List<String> {
        val requestDto = params.toEditsParamsDto()
        val response = chatGptApi.edits(requestDto)
        return response.getBodyOrThrow().toEditsModel()
    }
}
