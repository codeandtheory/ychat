package co.yml.ychat.data.api

import co.yml.ychat.core.network.infrastructure.ApiResult
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

internal interface ChatGptApi {

    suspend fun completion(paramsDto: CompletionParamsDto): ApiResult<CompletionDto>

    suspend fun chatCompletions(paramsDto: ChatCompletionParamsDto): ApiResult<ChatCompletionsDto>

    suspend fun imageGenerations(paramsDto: ImageGenerationsParamsDto): ApiResult<ImageGenerationsDto>

    suspend fun edits(paramsDto: EditsParamsDto): ApiResult<EditsDto>

    suspend fun models(): ApiResult<ModelListDto>

    suspend fun model(id: String): ApiResult<ModelDto>

    suspend fun audioTranscriptions(audioParamsDto: AudioParamsDto): ApiResult<AudioResultDto>

    suspend fun audioTranslations(audioParamsDto: AudioParamsDto): ApiResult<AudioResultDto>
}
