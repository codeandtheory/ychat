package co.yml.ychat

import co.yml.ychat.features.Completions
import co.yml.ychat.features.impl.YChatImpl
import co.yml.ychat.providers.Provider
import kotlin.jvm.JvmStatic
import kotlin.jvm.Volatile
import kotlin.native.concurrent.ThreadLocal

interface YChat {

    fun completions(): Completions

    @ThreadLocal
    companion object {

        @Volatile
        private var instance: YChat? = null

        @JvmStatic
        fun create(provider: Provider): YChat {
            return instance ?: YChatImpl(provider).also { instance = it }
        }
    }
}
