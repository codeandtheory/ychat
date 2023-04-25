package co.yml.ychat.entrypoint.impl

import co.yml.ychat.YChat
import co.yml.ychat.di.module.LibraryModule
import co.yml.ychat.entrypoint.features.AudioTranscriptions
import co.yml.ychat.entrypoint.features.AudioTranslations
import co.yml.ychat.entrypoint.features.ChatCompletions
import co.yml.ychat.entrypoint.features.Edits
import co.yml.ychat.entrypoint.features.ImageGenerations
import co.yml.ychat.entrypoint.features.ListModels
import co.yml.ychat.entrypoint.features.OpenAiCompletion
import co.yml.ychat.entrypoint.features.RetrieveModel
import org.koin.core.KoinApplication

internal class YChatImpl(apiKey: String) : YChat {

    val koinApp = KoinApplication.init()

    init {
        val modules = LibraryModule(apiKey).modules()
        koinApp.modules(modules)
    }

    override fun listModels(): ListModels {
        return koinApp.koin.get()
    }

    override fun retrieveModel(): RetrieveModel {
        return koinApp.koin.get()
    }

    override fun completion(): OpenAiCompletion {
        return koinApp.koin.get()
    }

    override fun chatCompletions(): ChatCompletions {
        return koinApp.koin.get()
    }

    override fun imageGenerations(): ImageGenerations {
        return koinApp.koin.get()
    }

    override fun edits(): Edits {
        return koinApp.koin.get()
    }

    override fun audioTranscriptions(): AudioTranscriptions {
        return koinApp.koin.get()
    }

    override fun audioTranslations(): AudioTranslations {
        return koinApp.koin.get()
    }
}
