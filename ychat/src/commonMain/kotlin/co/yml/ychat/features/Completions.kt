package co.yml.ychat.features.impl

import co.yml.ychat.core.exceptions.YChatException
import kotlin.coroutines.cancellation.CancellationException

interface Completions {
    /**
     * The default value of [input] is "".
     * @param input The prompt(s) to generate completions for.
     */
    fun setInput(input: String): Completions

    /**
     * Execute completion request.
     * @return one predicted completion of the given prompt.
     */
    @Throws(CancellationException::class, YChatException::class)
    suspend fun execute(): String

    /**
     * Executes completion request and returns the result through a callback.
     * @param callback a callback to return the result of the completion.
     */
    fun execute(callback: YChatCallback<String>)
}
