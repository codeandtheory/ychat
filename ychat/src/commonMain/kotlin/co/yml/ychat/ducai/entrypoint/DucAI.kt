package co.yml.ychat.ducai.entrypoint

import co.yml.ychat.ducai.entrypoint.features.DucAICompletions
import co.yml.ychat.entrypoint.features.Completion
import kotlin.jvm.JvmStatic
import kotlin.jvm.Volatile
import kotlin.native.concurrent.ThreadLocal

interface DucAI {

    fun completion(): DucAICompletions

}