package co.yml.ychat.di

import co.yml.ychat.data.api.ChatGptApi
import co.yml.ychat.data.infrastructure.ApiExecutor
import co.yml.ychat.data.storage.ChatLogStorage
import co.yml.ychat.di.module.LibraryModule
import co.yml.ychat.domain.usecases.ChatCompletionsUseCase
import co.yml.ychat.domain.usecases.CompletionUseCase
import co.yml.ychat.domain.usecases.ImageGenerationsUseCase
import co.yml.ychat.entrypoint.features.ChatCompletions
import co.yml.ychat.entrypoint.features.Completion
import co.yml.ychat.entrypoint.features.ImageGenerations
import io.ktor.client.HttpClient
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
        startKoin { modules(LibraryModule("api.key").modules()) }
    }

    @AfterTest
    fun tearDown() {
        stopKoin()
    }

    @Test
    fun `should inject all modules without throwing exception`() {
        get<Completion>()
        get<HttpClient>()
        get<ChatLogStorage>()
        get<ApiExecutor>()
        get<ChatGptApi>()
        get<CompletionUseCase>()
        get<ChatCompletionsUseCase>()
        get<ChatCompletions>()
        get<ImageGenerationsUseCase>()
        get<ImageGenerations>()
    }
}
