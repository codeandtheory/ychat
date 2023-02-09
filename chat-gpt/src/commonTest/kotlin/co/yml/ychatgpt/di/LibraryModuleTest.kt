package co.yml.ychatgpt.di

import co.yml.ychatgpt.data.api.ChatGptApi
import co.yml.ychatgpt.data.infrastructure.ApiExecutor
import co.yml.ychatgpt.data.storage.ChatLogStorage
import co.yml.ychatgpt.di.module.LibraryModule
import co.yml.ychatgpt.domain.usecases.CompletionUseCase
import co.yml.ychatgpt.entrypoint.features.Completion
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
    }
}
