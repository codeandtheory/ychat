package co.yml.ychat.ducai.entrypoint

import co.yml.ychat.entrypoint.features.Completion
import kotlin.jvm.JvmStatic
import kotlin.jvm.Volatile
import kotlin.native.concurrent.ThreadLocal

interface DucAI {

    fun completion(): Completion

    @ThreadLocal
    companion object {

        @Volatile
        private var instance: DucAI? = null

        @JvmStatic
        fun create(): DucAI {
            return instance ?: DucAIImpl().also { instance = it }
        }
    }

}