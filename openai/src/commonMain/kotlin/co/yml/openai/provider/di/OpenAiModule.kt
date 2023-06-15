package co.yml.openai.provider.di

import co.yml.ychat.core.network.factories.HttpClientFactory
import co.yml.ychat.core.network.infrastructure.ApiExecutor
import co.yml.openai.provider.data.api.OpenAiApi
import co.yml.openai.provider.data.api.impl.OpenAiApiImpl
import co.yml.openai.provider.data.infrastructure.OpenAiHttpClient
import co.yml.ychat.core.storage.ChatLogStorage
import co.yml.openai.provider.domain.usecases.AudioUseCase
import co.yml.openai.provider.domain.usecases.ChatCompletionsUseCase
import co.yml.openai.provider.domain.usecases.CompletionUseCase
import co.yml.openai.provider.domain.usecases.EditsUseCase
import co.yml.openai.provider.domain.usecases.ImageGenerationsUseCase
import co.yml.openai.provider.domain.usecases.ListModelsUseCase
import co.yml.openai.provider.domain.usecases.RetrieveModelUseCase
import co.yml.openai.provider.entrypoint.features.OpenAIAudioTranscriptions
import co.yml.openai.provider.entrypoint.features.OpenAIAudioTranslations
import co.yml.openai.provider.entrypoint.features.OpenAIChatCompletions
import co.yml.openai.provider.entrypoint.features.OpenAICompletion
import co.yml.openai.provider.entrypoint.features.OpenAIEdits
import co.yml.openai.provider.entrypoint.features.OpenAIImageGenerations
import co.yml.openai.provider.entrypoint.features.OpenAIListModels
import co.yml.openai.provider.entrypoint.features.OpenAIRetrieveModel
import co.yml.openai.provider.entrypoint.impl.OpenAIAudioTranscriptionsImpl
import co.yml.openai.provider.entrypoint.impl.OpenAIAudioTranslationsImpl
import co.yml.openai.provider.entrypoint.impl.OpenAIChatCompletionsImpl
import co.yml.openai.provider.entrypoint.impl.OpenAICompletionImpl
import co.yml.openai.provider.entrypoint.impl.OpenAIEditsImpl
import co.yml.openai.provider.entrypoint.impl.OpenAIImageGenerationsImpl
import co.yml.openai.provider.entrypoint.impl.OpenAIListModelsImpl
import co.yml.openai.provider.entrypoint.impl.OpenAIRetrieveModelImpl
import kotlinx.coroutines.Dispatchers
import org.koin.core.module.Module
import org.koin.dsl.module

internal class OpenAiModule(private val apiKey: String) {

    fun modules(): List<Module> =
        entrypointModule + domainModule + dataModule

    private val entrypointModule = module {
        factory<OpenAIListModels> { OpenAIListModelsImpl(Dispatchers.Default, get()) }
        factory<OpenAIRetrieveModel> { OpenAIRetrieveModelImpl(Dispatchers.Default, get()) }
        factory<OpenAICompletion> { OpenAICompletionImpl(Dispatchers.Default, get()) }
        factory<OpenAIChatCompletions> { OpenAIChatCompletionsImpl(Dispatchers.Default, get()) }
        factory<OpenAIImageGenerations> { OpenAIImageGenerationsImpl(Dispatchers.Default, get()) }
        factory<OpenAIEdits> { OpenAIEditsImpl(Dispatchers.Default, get()) }
        factory<OpenAIAudioTranscriptions> { OpenAIAudioTranscriptionsImpl(Dispatchers.Default, get()) }
        factory<OpenAIAudioTranslations> { OpenAIAudioTranslationsImpl(Dispatchers.Default, get()) }
    }

    private val domainModule = module {
        factory { ListModelsUseCase(get()) }
        factory { RetrieveModelUseCase(get()) }
        factory { CompletionUseCase(get(), get()) }
        factory { ChatCompletionsUseCase(get()) }
        factory { ImageGenerationsUseCase(get()) }
        factory { EditsUseCase(get()) }
        factory { AudioUseCase(get()) }
    }

    private val dataModule = module {
        single<HttpClientFactory> { OpenAiHttpClient(apiKey) }
        single { ChatLogStorage() }
        factory { ApiExecutor(get()) }
        factory<OpenAiApi> { OpenAiApiImpl(get()) }
    }
}
