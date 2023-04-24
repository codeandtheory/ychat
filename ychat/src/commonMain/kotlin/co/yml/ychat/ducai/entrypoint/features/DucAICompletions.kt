package co.yml.ychat.ducai.entrypoint.features

import co.yml.ychat.YChat
import co.yml.ychat.data.exception.ChatGptException
import kotlin.coroutines.cancellation.CancellationException

interface DucAICompletions {

    fun setInput(input: String): DucAICompletions

    @Throws(CancellationException::class, ChatGptException::class)
    suspend fun execute(): String

    fun execute(callback: YChat.Callback<String>)

}