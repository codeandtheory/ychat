package co.yml.openai.provider.data.api

import co.yml.ychat.core.network.infrastructure.ApiResult
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

internal interface OpenAiApi {

    suspend fun completion(paramsDto: CompletionParamsDto): ApiResult<CompletionDto>

    suspend fun chatCompletions(paramsDto: ChatCompletionParamsDto): ApiResult<ChatCompletionsDto>

    suspend fun imageGenerations(paramsDto: ImageGenerationsParamsDto): ApiResult<ImageGenerationsDto>

    suspend fun edits(paramsDto: EditsParamsDto): ApiResult<EditsDto>

    suspend fun models(): ApiResult<ModelListDto>

    suspend fun model(id: String): ApiResult<ModelDto>

    suspend fun audioTranscriptions(audioParamsDto: AudioParamsDto): ApiResult<AudioResultDto>

    suspend fun audioTranslations(audioParamsDto: AudioParamsDto): ApiResult<AudioResultDto>
}
