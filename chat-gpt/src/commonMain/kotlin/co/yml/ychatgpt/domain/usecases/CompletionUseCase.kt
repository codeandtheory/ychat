package co.yml.ychatgpt.domain.usecases

import co.yml.ychatgpt.data.api.ChatGptApi
import co.yml.ychatgpt.data.dto.CompletionDto
import co.yml.ychatgpt.data.infrastructure.ApiResult
import co.yml.ychatgpt.data.storage.ChatLogStorage
import co.yml.ychatgpt.domain.mapper.toCompletionModel
import co.yml.ychatgpt.domain.mapper.toCompletionParamsDto
import co.yml.ychatgpt.domain.model.CompletionModel
import co.yml.ychatgpt.domain.model.CompletionParams

internal class CompletionUseCase(
    private val chatGptApi: ChatGptApi,
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
