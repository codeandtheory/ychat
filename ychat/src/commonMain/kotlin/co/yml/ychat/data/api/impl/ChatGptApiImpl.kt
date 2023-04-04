package co.yml.ychat.data.api.impl

import co.yml.ychat.data.api.ChatGptApi
import co.yml.ychat.data.dto.AudioParamsDto
import co.yml.ychat.data.dto.AudioResultDto
import co.yml.ychat.data.dto.ChatCompletionParamsDto
import co.yml.ychat.data.dto.ChatCompletionsDto
import co.yml.ychat.data.dto.CompletionDto
import co.yml.ychat.data.dto.CompletionParamsDto
import co.yml.ychat.data.dto.EditsDto
import co.yml.ychat.data.dto.EditsParamsDto
import co.yml.ychat.data.dto.ImageGenerationsDto
import co.yml.ychat.data.dto.ImageGenerationsParamsDto
import co.yml.ychat.data.dto.ModelDto
import co.yml.ychat.data.dto.ModelListDto
import co.yml.ychat.data.infrastructure.ApiExecutor
import co.yml.ychat.data.infrastructure.ApiResult
import co.yml.ychat.domain.model.toByteArray
import io.ktor.http.HttpMethod

internal class ChatGptApiImpl(private val apiExecutor: ApiExecutor) : ChatGptApi {

    override suspend fun completion(paramsDto: CompletionParamsDto): ApiResult<CompletionDto> {
        return apiExecutor
            .setEndpoint("v1/completions")
            .setHttpMethod(HttpMethod.Post)
            .setBody(paramsDto)
            .execute()
    }

    override suspend fun chatCompletions(paramsDto: ChatCompletionParamsDto): ApiResult<ChatCompletionsDto> {
        return apiExecutor
            .setEndpoint("v1/chat/completions")
            .setHttpMethod(HttpMethod.Post)
            .setBody(paramsDto)
            .execute()
    }

    override suspend fun imageGenerations(paramsDto: ImageGenerationsParamsDto): ApiResult<ImageGenerationsDto> {
        return apiExecutor
            .setEndpoint("v1/images/generations")
            .setHttpMethod(HttpMethod.Post)
            .setBody(paramsDto)
            .execute()
    }

    override suspend fun edits(paramsDto: EditsParamsDto): ApiResult<EditsDto> {
        return apiExecutor
            .setEndpoint("v1/edits")
            .setHttpMethod(HttpMethod.Post)
            .setBody(paramsDto)
            .execute()
    }

    override suspend fun models(): ApiResult<ModelListDto> {
        return apiExecutor
            .setEndpoint("v1/models")
            .setHttpMethod(HttpMethod.Get)
            .execute()
    }

    override suspend fun model(id: String): ApiResult<ModelDto> {
        return apiExecutor
            .setEndpoint("v1/models/$id")
            .setHttpMethod(HttpMethod.Get)
            .execute()
    }

    override suspend fun audioTranscriptions(audioParamsDto: AudioParamsDto): ApiResult<AudioResultDto> {
        val byteArray = audioParamsDto.byteArray.toByteArray()
        val apiBuilder = apiExecutor
            .setEndpoint("v1/audio/transcriptions")
            .setHttpMethod(HttpMethod.Post)
            .addFormPart("file", audioParamsDto.filename, byteArray)
        audioParamsDto.getMap().forEach { apiBuilder.addFormPart(it.key, it.value) }
        return apiBuilder.execute()
    }
}
