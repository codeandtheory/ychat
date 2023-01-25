package co.yml.ychatgpt.di.module

import co.yml.ychatgpt.data.api.ChatGptApi
import co.yml.ychatgpt.data.api.impl.ChatGptApiImpl
import co.yml.ychatgpt.data.infrastructure.ApiExecutor
import co.yml.ychatgpt.data.storage.ChatLogStorage
import co.yml.ychatgpt.di.provider.NetworkProvider
import co.yml.ychatgpt.domain.usecases.CompletionUseCase
import org.koin.core.module.Module
import org.koin.dsl.module

internal class LibraryModule(private val apiKey: String) {

    fun modules(): List<Module> =
        domainModule + dataModule + platformModule()


    private val domainModule = module {
        factory { CompletionUseCase(get(), get()) }
    }

    private val dataModule = module {
        single { NetworkProvider.provideHttpClient(get(), apiKey) }
        single { ChatLogStorage() }
        factory { ApiExecutor(get()) }
        factory<ChatGptApi> { ChatGptApiImpl(get()) }
    }
}

internal expect fun platformModule(): Module
