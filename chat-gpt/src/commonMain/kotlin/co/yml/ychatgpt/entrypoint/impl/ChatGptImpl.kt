package co.yml.ychatgpt.entrypoint.impl

import co.yml.ychatgpt.ChatGpt
import co.yml.ychatgpt.di.module.LibraryModule
import co.yml.ychatgpt.domain.usecases.CompletionUseCase
import co.yml.ychatgpt.entrypoint.model.CompletionParams
import org.koin.core.KoinApplication

internal class ChatGptImpl(apiKey: String) : ChatGpt {

    private val koinApp = KoinApplication.init()

    private val completionUseCase by lazy { koinApp.koin.get<CompletionUseCase>() }

    init {
        val modules = LibraryModule(apiKey).modules()
        koinApp.modules(modules)
    }

    override suspend fun completion(input: String): String {
        return completion(input, CompletionParams())
    }

    override suspend fun completion(
        input: String,
        completionParams: CompletionParams
    ): String {
        return completionUseCase.completion(input, completionParams)
            .choices
            .first()
            .text
            .trim()
    }
}
