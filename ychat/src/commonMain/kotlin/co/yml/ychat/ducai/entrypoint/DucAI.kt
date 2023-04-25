package co.yml.ychat.ducai.entrypoint

import co.yml.ychat.Provider
import co.yml.ychat.ducai.entrypoint.features.DucAICompletions

interface DucAI : Provider {

    fun completion(): DucAICompletions

}