package co.yml.ychat.features.impl

import co.yml.ychat.YChat
import co.yml.ychat.di.YChatModule
import co.yml.ychat.features.Completions
import co.yml.ychat.providers.Provider
import org.koin.core.KoinApplication

internal class YChatImpl(provider: Provider) : YChat {

    val koinApp = KoinApplication.init()

    init {
        val modules = YChatModule(provider).modules()
        koinApp.modules(modules)
    }

    override fun completions(): Completions {
        return koinApp.koin.get()
    }
}
