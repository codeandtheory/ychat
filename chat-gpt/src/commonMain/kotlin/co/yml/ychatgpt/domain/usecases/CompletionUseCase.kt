package co.yml.ychatgpt.domain.usecases

import co.yml.ychatgpt.data.api.ChatGptApi
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
        val inputRequest =
            if (completionParams.enableChatStorage) chatLogStorage.buildChatInput(input)
            else input
        val chatLog = chatLogStorage.buildChatInput(inputRequest)
        val completionDto = completionParams.toCompletionParamsDto(chatLog)
        val response = chatGptApi.completion(completionDto)
        if (!response.isSuccessful) {
            chatLogStorage.removeLastAppendedInput()
        }
        return response.getBodyOrThrow()
            .toCompletionModel()
            .also {
                val answer = it.choices.first().text.trim()
                chatLogStorage.appendAnswer(answer)
            }
    }
}
