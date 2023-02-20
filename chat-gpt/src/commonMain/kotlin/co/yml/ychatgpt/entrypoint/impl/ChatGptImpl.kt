package co.yml.ychatgpt.entrypoint.impl

import co.yml.ychatgpt.YChatGpt
import co.yml.ychatgpt.di.module.LibraryModule
import co.yml.ychatgpt.entrypoint.features.Completion
import org.koin.core.KoinApplication

internal class ChatGptImpl(apiKey: String) : YChatGpt {

    val koinApp = KoinApplication.init()

    init {
        val modules = LibraryModule(apiKey).modules()
        koinApp.modules(modules)
    }

    override fun completion(): Completion {
        return koinApp.koin.get()
    }
}
