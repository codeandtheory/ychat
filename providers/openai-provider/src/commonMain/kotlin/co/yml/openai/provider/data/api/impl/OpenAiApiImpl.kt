package co.yml.openai.provider.data.api.impl

import co.yml.ychat.core.model.toByteArray
import co.yml.ychat.core.network.infrastructure.ApiExecutor
import co.yml.ychat.core.network.infrastructure.ApiResult
import co.yml.openai.provider.data.api.OpenAiApi
import co.yml.openai.provider.data.dto.AudioParamsDto
import co.yml.openai.provider.data.dto.AudioResultDto
import co.yml.openai.provider.data.dto.ChatCompletionParamsDto
import co.yml.openai.provider.data.dto.ChatCompletionsDto
import co.yml.openai.provider.data.dto.CompletionDto
import co.yml.openai.provider.data.dto.CompletionParamsDto
import co.yml.openai.provider.data.dto.EditsDto
import co.yml.openai.provider.data.dto.EditsParamsDto
import co.yml.openai.provider.data.dto.ImageGenerationsDto
import co.yml.openai.provider.data.dto.ImageGenerationsParamsDto
import co.yml.openai.provider.data.dto.ModelDto
import co.yml.openai.provider.data.dto.ModelListDto
import io.ktor.http.HttpMethod

internal class OpenAiApiImpl(private val apiExecutor: ApiExecutor) : OpenAiApi {

    override suspend fun completion(paramsDto: CompletionParamsDto): ApiResult<CompletionDto> {
        return apiExecutor
            .setEndpoint("$VERSION/completions")
            .setHttpMethod(HttpMethod.Post)
            .setBody(paramsDto)
            .execute()
    }

    override suspend fun chatCompletions(paramsDto: ChatCompletionParamsDto): ApiResult<ChatCompletionsDto> {
        return apiExecutor
            .setEndpoint("$VERSION/chat/completions")
            .setHttpMethod(HttpMethod.Post)
            .setBody(paramsDto)
            .execute()
    }

    override suspend fun imageGenerations(paramsDto: ImageGenerationsParamsDto): ApiResult<ImageGenerationsDto> {
        return apiExecutor
            .setEndpoint("$VERSION/images/generations")
            .setHttpMethod(HttpMethod.Post)
            .setBody(paramsDto)
            .execute()
    }

    override suspend fun edits(paramsDto: EditsParamsDto): ApiResult<EditsDto> {
        return apiExecutor
            .setEndpoint("$VERSION/edits")
            .setHttpMethod(HttpMethod.Post)
            .setBody(paramsDto)
            .execute()
    }

    override suspend fun models(): ApiResult<ModelListDto> {
        return apiExecutor
            .setEndpoint("$VERSION/models")
            .setHttpMethod(HttpMethod.Get)
            .execute()
    }

    override suspend fun model(id: String): ApiResult<ModelDto> {
        return apiExecutor
            .setEndpoint("$VERSION/models/$id")
            .setHttpMethod(HttpMethod.Get)
            .execute()
    }

    override suspend fun audioTranscriptions(audioParamsDto: AudioParamsDto): ApiResult<AudioResultDto> {
        val byteArray = audioParamsDto.byteArray.toByteArray()
        val apiBuilder = apiExecutor
            .setEndpoint("$VERSION/audio/transcriptions")
            .setHttpMethod(HttpMethod.Post)
            .addFormPart("file", audioParamsDto.filename, byteArray)
        audioParamsDto.getMap().forEach { apiBuilder.addFormPart(it.key, it.value) }
        return apiBuilder.execute()
    }

    override suspend fun audioTranslations(audioParamsDto: AudioParamsDto): ApiResult<AudioResultDto> {
        val byteArray = audioParamsDto.byteArray.toByteArray()
        val apiBuilder = apiExecutor
            .setEndpoint("$VERSION/audio/translations")
            .setHttpMethod(HttpMethod.Post)
            .addFormPart("file", audioParamsDto.filename, byteArray)
        audioParamsDto.getMap().forEach { apiBuilder.addFormPart(it.key, it.value) }
        return apiBuilder.execute()
    }

    companion object {
        private const val VERSION = "v1"
    }
}
