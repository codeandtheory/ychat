package co.yml.ychat.di.module

import co.yml.ychat.data.infrastructure.ApiExecutor
import co.yml.ychat.di.provider.NetworkProvider
import co.yml.ychat.ducai.data.api.BASE_URL
import co.yml.ychat.ducai.data.api.DucAIApi
import co.yml.ychat.ducai.data.api.impl.DucAIApiImpl
import co.yml.ychat.ducai.domain.usecases.CompletionDucAIUseCase
import co.yml.ychat.ducai.entrypoint.DucAI
import co.yml.ychat.ducai.entrypoint.DucAIImpl
import co.yml.ychat.ducai.entrypoint.features.DucAICompletions
import co.yml.ychat.ducai.entrypoint.impl.DucAICompletionsImpl
import kotlinx.coroutines.Dispatchers
import org.koin.core.module.Module
import org.koin.dsl.module

internal class DucAILibraryModule() {

    fun modules(): List<Module> =
        apiModule + entrypointModule + domainModule + platformModule()


    private val entrypointModule = module {
        factory<DucAICompletions> { DucAICompletionsImpl(Dispatchers.Default, get()) }
    }

    private val domainModule = module {
        factory { CompletionDucAIUseCase(get()) }
    }

    private val apiModule = module {
        single { NetworkProvider.provideHttpClient(
            engine = get(),
            baseUrl = BASE_URL
        ) }
        factory { ApiExecutor(get()) }
        factory<DucAIApi> { DucAIApiImpl(get()) }
        factory<DucAI> { DucAIImpl(get()) }
    }
}

