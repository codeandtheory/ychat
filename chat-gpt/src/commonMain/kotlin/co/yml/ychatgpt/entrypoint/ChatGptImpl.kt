package co.yml.ychatgpt.entrypoint

import co.yml.ychatgpt.ChatGpt
import co.yml.ychatgpt.di.module.LibraryModule
import org.koin.core.KoinApplication
import org.koin.core.context.startKoin

internal class ChatGptImpl(apiKey: String) : ChatGpt {

    private var koinApp: KoinApplication

    init {
        val modules = LibraryModule(apiKey).modules()
        koinApp = startKoin { modules(modules) }
    }

    override suspend fun completion(input: String): String {
        return completion(input, CompletionParams())
    }

    override suspend fun completion(input: String, completionParams: CompletionParams): String {
        // todo: Implement chat gpt completion request api.
        return "Hello world"
    }
}
