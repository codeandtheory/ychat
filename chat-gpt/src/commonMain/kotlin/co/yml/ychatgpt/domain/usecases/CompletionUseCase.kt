package co.yml.ychatgpt.domain.usecases

import co.yml.ychatgpt.data.api.ChatGptApi
import co.yml.ychatgpt.data.dto.CompletionDto
import co.yml.ychatgpt.data.infrastructure.ApiResult
import co.yml.ychatgpt.data.storage.ChatLogStorage
import co.yml.ychatgpt.domain.mapper.toCompletionModel
import co.yml.ychatgpt.domain.model.CompletionModel
import co.yml.ychatgpt.entrypoint.mapper.toCompletionParamsDto
import co.yml.ychatgpt.entrypoint.model.CompletionParams

internal class CompletionUseCase(
    private val chatGptApi: ChatGptApi,
    private val chatLogStorage: ChatLogStorage
) {

    suspend fun completion(
        input: String,
        completionParams: CompletionParams
    ): CompletionModel {
        val response =
            if (completionParams.enableChatStorage) chatLogCompletion(input, completionParams)
            else requestCompletion(input, completionParams)
        return response.getBodyOrThrow().toCompletionModel()
    }

    private suspend fun requestCompletion(
        input: String,
        completionParams: CompletionParams
    ): ApiResult<CompletionDto> {
        val completionDto = completionParams.toCompletionParamsDto(input)
        return chatGptApi.completion(completionDto)
    }

    private suspend fun chatLogCompletion(
        input: String,
        completionParams: CompletionParams
    ): ApiResult<CompletionDto> {
        val inputChatLog = chatLogStorage.buildChatInput(input)
        val response = requestCompletion(inputChatLog, completionParams)
        if (!response.isSuccessful) {
            chatLogStorage.removeLastAppendedInput()
        } else {
            val answer = response.body?.choices?.first()?.text.orEmpty()
            chatLogStorage.appendAnswer(answer)
        }
        return response
    }
}
