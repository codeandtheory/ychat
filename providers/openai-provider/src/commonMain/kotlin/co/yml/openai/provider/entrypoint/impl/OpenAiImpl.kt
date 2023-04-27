package co.yml.openai.provider.entrypoint.impl

import co.yml.openai.provider.OpenAi
import co.yml.openai.provider.entrypoint.features.OpenAiAudioTranscriptions
import co.yml.openai.provider.entrypoint.features.OpenAiAudioTranslations
import co.yml.openai.provider.entrypoint.features.OpenAiChatCompletions
import co.yml.openai.provider.entrypoint.features.OpenAiCompletion
import co.yml.openai.provider.entrypoint.features.OpenAiEdits
import co.yml.openai.provider.entrypoint.features.OpenAiImageGenerations
import co.yml.openai.provider.entrypoint.features.OpenAiListModels
import co.yml.openai.provider.entrypoint.features.OpenAiRetrieveModel
import co.yml.openai.provider.di.OpenAiModule
import org.koin.core.KoinApplication

internal class OpenAiImpl(apiKey: String) : OpenAi {

    val koinApp = KoinApplication.init()

    init {
        val modules = OpenAiModule(apiKey).modules()
        koinApp.modules(modules)
    }

    override fun listModels(): OpenAiListModels {
        return koinApp.koin.get()
    }

    override fun retrieveModel(): OpenAiRetrieveModel {
        return koinApp.koin.get()
    }

    override fun completion(): OpenAiCompletion {
        return koinApp.koin.get()
    }

    override fun chatCompletions(): OpenAiChatCompletions {
        return koinApp.koin.get()
    }

    override fun imageGenerations(): OpenAiImageGenerations {
        return koinApp.koin.get()
    }

    override fun edits(): OpenAiEdits {
        return koinApp.koin.get()
    }

    override fun audioTranscriptions(): OpenAiAudioTranscriptions {
        return koinApp.koin.get()
    }

    override fun audioTranslations(): OpenAiAudioTranslations {
        return koinApp.koin.get()
    }
}
