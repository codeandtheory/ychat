package co.yml.ychat.di

import co.yml.openai.provider.data.api.OpenAiApi
import co.yml.openai.provider.di.OpenAiModule
import co.yml.openai.provider.domain.usecases.AudioUseCase
import co.yml.openai.provider.domain.usecases.ChatCompletionsUseCase
import co.yml.openai.provider.domain.usecases.CompletionUseCase
import co.yml.openai.provider.domain.usecases.EditsUseCase
import co.yml.openai.provider.domain.usecases.ImageGenerationsUseCase
import co.yml.openai.provider.domain.usecases.ListModelsUseCase
import co.yml.openai.provider.entrypoint.features.OpenAiAudioTranscriptions
import co.yml.openai.provider.entrypoint.features.OpenAiChatCompletions
import co.yml.openai.provider.entrypoint.features.OpenAiCompletion
import co.yml.openai.provider.entrypoint.features.OpenAiEdits
import co.yml.openai.provider.entrypoint.features.OpenAiImageGenerations
import co.yml.openai.provider.entrypoint.features.OpenAiListModels
import co.yml.ychat.core.network.factories.HttpClientFactory
import co.yml.ychat.core.network.infrastructure.ApiExecutor
import co.yml.ychat.core.storage.ChatLogStorage
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.test.KoinTest
import org.koin.test.get

class LibraryModuleTest : KoinTest {

    @BeforeTest
    fun setup() {
        startKoin { modules(OpenAiModule("api.key").modules()) }
    }

    @AfterTest
    fun tearDown() {
        stopKoin()
    }

    @Test
    fun `should inject all entrypoint modules without throwing exception`() {
        get<OpenAiCompletion>()
        get<OpenAiChatCompletions>()
        get<OpenAiImageGenerations>()
        get<OpenAiEdits>()
        get<OpenAiListModels>()
        get<OpenAiAudioTranscriptions>()
    }

    @Test
    fun `should inject all domain modules without throwing exception`() {
        get<ListModelsUseCase>()
        get<CompletionUseCase>()
        get<ChatCompletionsUseCase>()
        get<ImageGenerationsUseCase>()
        get<EditsUseCase>()
        get<AudioUseCase>()
    }

    @Test
    fun `should inject all data modules without throwing exception`() {
        get<HttpClientFactory>()
        get<ChatLogStorage>()
        get<ApiExecutor>()
        get<OpenAiApi>()
    }
}
