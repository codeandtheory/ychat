package co.yml.ychatgpt.di.module

import co.yml.ychatgpt.di.provider.NetworkProvider
import org.koin.core.module.Module
import org.koin.dsl.module

internal class LibraryModule(private val apiKey: String) {

    fun modules(): List<Module> =
        dataModule + platformModule()

    private val dataModule = module {
        single { NetworkProvider.provideHttpClient(get(), apiKey) }
    }
}

internal expect fun platformModule(): Module