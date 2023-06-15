package co.yml.openai.provider.domain.usecases

import co.yml.ychat.core.network.infrastructure.ApiResult
import co.yml.openai.provider.data.api.OpenAiApi
import co.yml.openai.provider.data.dto.CompletionDto
import co.yml.ychat.core.storage.ChatLogStorage
import co.yml.openai.provider.domain.mapper.toCompletionModel
import co.yml.openai.provider.domain.mapper.toCompletionParamsDto
import co.yml.openai.provider.domain.model.CompletionModel
import co.yml.openai.provider.domain.model.CompletionParams

internal class CompletionUseCase(
    private val chatGptApi: OpenAiApi,
    private val chatLogStorage: ChatLogStorage
) {

    suspend fun completion(completionParams: CompletionParams): CompletionModel {
        val response =
            if (completionParams.enableChatStorage) chatLogCompletion(completionParams)
            else requestCompletion(completionParams)
        return response.getBodyOrThrow().toCompletionModel()
    }

    private suspend fun requestCompletion(
        completionParams: CompletionParams
    ): ApiResult<CompletionDto> {
        val completionDto = completionParams.toCompletionParamsDto()
        return chatGptApi.completion(completionDto)
    }

    private suspend fun chatLogCompletion(
        completionParams: CompletionParams
    ): ApiResult<CompletionDto> {
        val inputChatLog = chatLogStorage.buildChatInput(completionParams.prompt)
        val response = requestCompletion(completionParams.copy(prompt = inputChatLog))
        if (!response.isSuccessful) {
            chatLogStorage.removeLastAppendedInput()
        } else {
            val answer = response.body?.choices?.first()?.text.orEmpty()
            chatLogStorage.appendAnswer(answer)
        }
        return response
    }
}
