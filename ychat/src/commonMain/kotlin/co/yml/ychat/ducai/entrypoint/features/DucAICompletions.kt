package co.yml.ychat.ducai.entrypoint.features

import co.yml.ychat.YChat
import co.yml.ychat.data.exception.ChatGptException
import co.yml.ychat.provider.Completions
import kotlin.coroutines.cancellation.CancellationException

interface DucAICompletions : Completions {

    fun setInput(input: String): DucAICompletions

    @Throws(CancellationException::class, ChatGptException::class)
    override suspend fun execute(): String

    fun execute(callback: YChat.Callback<String>)

}