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
import co.yml.openai.provider.entrypoint.features.OpenAiAudioTranscriptions
import co.yml.openai.provider.entrypoint.features.OpenAiAudioTranslations
import co.yml.openai.provider.entrypoint.features.OpenAiChatCompletions
import co.yml.openai.provider.entrypoint.features.OpenAiCompletion
import co.yml.openai.provider.entrypoint.features.OpenAiEdits
import co.yml.openai.provider.entrypoint.features.OpenAiImageGenerations
import co.yml.openai.provider.entrypoint.features.OpenAiListModels
import co.yml.openai.provider.entrypoint.features.OpenAiRetrieveModel
import co.yml.openai.provider.entrypoint.impl.OpenAiAudioTranscriptionsImpl
import co.yml.openai.provider.entrypoint.impl.OpenAiAudioTranslationsImpl
import co.yml.openai.provider.entrypoint.impl.OpenAiChatCompletionsImpl
import co.yml.openai.provider.entrypoint.impl.OpenAiCompletionImpl
import co.yml.openai.provider.entrypoint.impl.OpenAiEditsImpl
import co.yml.openai.provider.entrypoint.impl.OpenAiImageGenerationsImpl
import co.yml.openai.provider.entrypoint.impl.OpenAiListModelsImpl
import co.yml.openai.provider.entrypoint.impl.OpenAiRetrieveModelImpl
import kotlinx.coroutines.Dispatchers
import org.koin.core.module.Module
import org.koin.dsl.module

internal class OpenAiModule(private val apiKey: String) {

    fun modules(): List<Module> =
        entrypointModule + domainModule + dataModule

    private val entrypointModule = module {
        factory<OpenAiListModels> { OpenAiListModelsImpl(Dispatchers.Default, get()) }
        factory<OpenAiRetrieveModel> { OpenAiRetrieveModelImpl(Dispatchers.Default, get()) }
        factory<OpenAiCompletion> { OpenAiCompletionImpl(Dispatchers.Default, get()) }
        factory<OpenAiChatCompletions> { OpenAiChatCompletionsImpl(Dispatchers.Default, get()) }
        factory<OpenAiImageGenerations> { OpenAiImageGenerationsImpl(Dispatchers.Default, get()) }
        factory<OpenAiEdits> { OpenAiEditsImpl(Dispatchers.Default, get()) }
        factory<OpenAiAudioTranscriptions> { OpenAiAudioTranscriptionsImpl(Dispatchers.Default, get()) }
        factory<OpenAiAudioTranslations> { OpenAiAudioTranslationsImpl(Dispatchers.Default, get()) }
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
