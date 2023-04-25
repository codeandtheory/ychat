package co.yml.ychat.di

import co.yml.ychat.data.api.ChatGptApi
import co.yml.ychat.data.infrastructure.ApiExecutor
import co.yml.ychat.data.storage.ChatLogStorage
import co.yml.ychat.di.module.LibraryModule
import co.yml.ychat.domain.usecases.AudioUseCase
import co.yml.ychat.domain.usecases.ChatCompletionsUseCase
import co.yml.ychat.domain.usecases.CompletionUseCase
import co.yml.ychat.domain.usecases.EditsUseCase
import co.yml.ychat.domain.usecases.ImageGenerationsUseCase
import co.yml.ychat.domain.usecases.ListModelsUseCase
import co.yml.ychat.entrypoint.features.AudioTranscriptions
import co.yml.ychat.entrypoint.features.ChatCompletions
import co.yml.ychat.entrypoint.features.Edits
import co.yml.ychat.entrypoint.features.ImageGenerations
import co.yml.ychat.entrypoint.features.ListModels
import co.yml.ychat.entrypoint.features.OpenAiCompletion
import io.ktor.client.HttpClient
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.test.KoinTest
import org.koin.test.get
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test

class LibraryModuleTest : KoinTest {

    @BeforeTest
    fun setup() {
        startKoin { modules(LibraryModule("api.key").modules()) }
    }

    @AfterTest
    fun tearDown() {
        stopKoin()
    }

    @Test
    fun `should inject all entrypoint modules without throwing exception`() {
        get<OpenAiCompletion>()
        get<ChatCompletions>()
        get<ImageGenerations>()
        get<Edits>()
        get<ListModels>()
        get<AudioTranscriptions>()
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
        get<HttpClient>()
        get<ChatLogStorage>()
        get<ApiExecutor>()
        get<ChatGptApi>()
    }
}
