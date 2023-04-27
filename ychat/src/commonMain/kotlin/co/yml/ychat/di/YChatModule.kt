package co.yml.ychat.di

import co.yml.ychat.features.Completions
import co.yml.ychat.features.impl.CompletionsImpl
import co.yml.ychat.providers.Provider
import kotlinx.coroutines.Dispatchers
import org.koin.core.module.Module
import org.koin.dsl.module

internal class YChatModule(private val provider: Provider) {

    fun modules(): List<Module> =
        listOf(featuresModule)

    private val featuresModule = module {
        factory<Completions> { CompletionsImpl(Dispatchers.Default, provider) }
    }
}
