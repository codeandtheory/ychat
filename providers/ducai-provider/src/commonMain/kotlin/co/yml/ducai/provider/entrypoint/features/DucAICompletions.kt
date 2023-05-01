package co.yml.ducai.provider.entrypoint.features

import co.yml.ducai.provider.DucAI
import co.yml.ychat.core.exceptions.YChatException
import kotlin.coroutines.cancellation.CancellationException

interface DucAICompletions {

    fun setInput(input: String): DucAICompletions

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
    fun execute(callback: DucAI.Callback<String>)
}
