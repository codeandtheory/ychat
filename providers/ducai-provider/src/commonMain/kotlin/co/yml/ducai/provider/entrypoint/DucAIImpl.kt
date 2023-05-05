package co.yml.ducai.provider.entrypoint

import co.yml.ducai.provider.DucAI
import co.yml.ducai.provider.di.DucAILibraryModule
import co.yml.ducai.provider.entrypoint.features.DucAICompletions
import org.koin.core.KoinApplication

internal class DucAIImpl : DucAI {

    private val koinApp = KoinApplication.init()

    init {
        val modules = DucAILibraryModule().modules()
        koinApp.modules(modules)
    }

    override fun completion(): DucAICompletions {
        return koinApp.koin.get()
    }
}
