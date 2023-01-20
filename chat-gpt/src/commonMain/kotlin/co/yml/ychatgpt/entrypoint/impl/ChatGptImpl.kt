package co.yml.ychatgpt.entrypoint.impl

import co.yml.ychatgpt.ChatGpt
import co.yml.ychatgpt.data.api.ChatGptApi
import co.yml.ychatgpt.data.storage.ChatLogStorage
import co.yml.ychatgpt.di.module.LibraryModule
import co.yml.ychatgpt.entrypoint.mapper.toCompletionParamsDto
import co.yml.ychatgpt.entrypoint.model.CompletionParams
import org.koin.core.KoinApplication
import org.koin.core.context.startKoin

internal class ChatGptImpl(apiKey: String) : ChatGpt {

    private lateinit var koinApp: KoinApplication

    private val chatGptApi by lazy { koinApp.koin.get<ChatGptApi>() }

    private val chatLogStorage by lazy { koinApp.koin.get<ChatLogStorage>() }

    init {
        val modules = LibraryModule(apiKey).modules()
        koinApp = startKoin { modules(modules) }
    }

    override suspend fun completion(input: String): String {
        return completion(input, CompletionParams())
    }

    override suspend fun completion(
        input: String,
        completionParams: CompletionParams
    ): String {
        val chatLog = chatLogStorage.getChatLog(input)
        val completionDto = completionParams.toCompletionParamsDto(chatLog)
        val response = chatGptApi.completion(completionDto)
        if (!response.isSuccessful) {
            chatLogStorage.removeLastAppendedInput()
        }
        return response.getBodyOrThrow()
            .choices
            .first()
            .text
            .also { chatLogStorage.appendAnswer(it) }
    }
}
