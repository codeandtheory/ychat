package co.yml.ychat.provider

import co.yml.ychat.data.exception.ChatGptException
import kotlin.coroutines.cancellation.CancellationException

interface Completions {

    @Throws(CancellationException::class, ChatGptException::class)
    suspend fun execute(): String

}
