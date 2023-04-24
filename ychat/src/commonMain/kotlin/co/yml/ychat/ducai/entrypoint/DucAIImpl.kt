package co.yml.ychat.ducai.entrypoint

import co.yml.ychat.di.module.DucAILibraryModule
import co.yml.ychat.ducai.entrypoint.features.DucAICompletions
import co.yml.ychat.entrypoint.features.Completion
import org.koin.core.KoinApplication

class DucAIImpl(
    val completions : DucAICompletions
) : DucAI {

    private val koinApp = KoinApplication.init()

    init {
        val modules = DucAILibraryModule().modules()
        koinApp.modules(modules)
    }

    override fun completion(): DucAICompletions {
        return completions
    }

}