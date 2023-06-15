package co.yml.openai.provider.domain.usecases

import co.yml.openai.provider.data.api.OpenAiApi
import co.yml.openai.provider.domain.mapper.toImageGenerated
import co.yml.openai.provider.domain.mapper.toImageGenerationsParamsDto
import co.yml.openai.provider.domain.model.ImageGenerationsParams

internal class ImageGenerationsUseCase(private val chatGptApi: OpenAiApi) {

    suspend fun requestImageGenerations(params: ImageGenerationsParams): List<String> {
        val requestDto = params.toImageGenerationsParamsDto()
        val response = chatGptApi.imageGenerations(requestDto)
        return response.getBodyOrThrow().toImageGenerated()
    }
}
