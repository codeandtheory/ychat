package co.yml.openai.provider.entrypoint.impl

import co.yml.openai.provider.OpenAI
import co.yml.openai.provider.di.OpenAiModule
import co.yml.openai.provider.entrypoint.features.OpenAIAudioTranslations
import co.yml.openai.provider.entrypoint.features.OpenAIChatCompletions
import co.yml.openai.provider.entrypoint.features.OpenAICompletion
import co.yml.openai.provider.entrypoint.features.OpenAIEdits
import co.yml.openai.provider.entrypoint.features.OpenAIImageGenerations
import co.yml.openai.provider.entrypoint.features.OpenAIListModels
import co.yml.openai.provider.entrypoint.features.OpenAIRetrieveModel
import org.koin.core.KoinApplication

internal class OpenAIImpl(apiKey: String) : OpenAI {

    val koinApp = KoinApplication.init()

    init {
        val modules = OpenAiModule(apiKey).modules()
        koinApp.modules(modules)
    }

    override fun listModels(): OpenAIListModels {
        return koinApp.koin.get()
    }

    override fun retrieveModel(): OpenAIRetrieveModel {
        return koinApp.koin.get()
    }

    override fun completion(): OpenAICompletion {
        return koinApp.koin.get()
    }

    override fun chatCompletions(): OpenAIChatCompletions {
        return koinApp.koin.get()
    }

    override fun imageGenerations(): OpenAIImageGenerations {
        return koinApp.koin.get()
    }

    override fun edits(): OpenAIEdits {
        return koinApp.koin.get()
    }

    override fun audioTranscriptions(): OpenAIAudioTranslations {
        return koinApp.koin.get()
    }

    override fun audioTranslations(): OpenAIAudioTranslations {
        return koinApp.koin.get()
    }
}
