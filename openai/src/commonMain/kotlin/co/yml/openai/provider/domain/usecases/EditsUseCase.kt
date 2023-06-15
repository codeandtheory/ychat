package co.yml.openai.provider.domain.usecases

import co.yml.openai.provider.data.api.OpenAiApi
import co.yml.openai.provider.domain.mapper.toEditsModel
import co.yml.openai.provider.domain.mapper.toEditsParamsDto
import co.yml.openai.provider.domain.model.EditsParams

internal class EditsUseCase(private val chatGptApi: OpenAiApi) {

    suspend fun requestEdits(params: EditsParams): List<String> {
        val requestDto = params.toEditsParamsDto()
        val response = chatGptApi.edits(requestDto)
        return response.getBodyOrThrow().toEditsModel()
    }
}
