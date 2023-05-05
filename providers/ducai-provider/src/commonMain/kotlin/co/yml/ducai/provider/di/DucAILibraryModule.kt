package co.yml.ducai.provider.di

import co.yml.ducai.provider.data.api.DucAIApi
import co.yml.ducai.provider.data.api.impl.DucAIApiImpl
import co.yml.ducai.provider.data.infrastructure.DucAiHttpClient
import co.yml.ducai.provider.domain.usecases.CompletionDucAIUseCase
import co.yml.ducai.provider.entrypoint.features.DucAICompletions
import co.yml.ducai.provider.entrypoint.impl.DucAICompletionsImpl
import co.yml.ychat.core.network.factories.HttpClientFactory
import co.yml.ychat.core.network.infrastructure.ApiExecutor
import kotlinx.coroutines.Dispatchers
import org.koin.core.module.Module
import org.koin.dsl.module

internal class DucAILibraryModule {

    fun modules(): List<Module> =
        dataModule + entrypointModule + domainModule

    private val entrypointModule = module {
        factory<DucAICompletions> { DucAICompletionsImpl(get(), Dispatchers.Default) }
    }

    private val domainModule = module {
        factory { CompletionDucAIUseCase(get()) }
    }

    private val dataModule = module {
        single<HttpClientFactory> { DucAiHttpClient() }
        factory { ApiExecutor(get()) }
        factory<DucAIApi> { DucAIApiImpl(get()) }
    }
}
