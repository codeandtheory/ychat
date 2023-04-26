package co.yml.ychat.ducai.entrypoint

import co.yml.ychat.ducai.entrypoint.features.DucAICompletions
import co.yml.ychat.provider.Provider

interface DucAI : Provider {

    fun completion(): DucAICompletions

}
