package co.yml.ychat.features

import co.yml.ychat.core.exceptions.YChatException
import kotlin.coroutines.cancellation.CancellationException

interface Completions {
    /**
     * The default value of [prompt] is "".
     * @param prompt The prompt(s) to generate completions for.
     */
    fun setPrompt(prompt: String): Completions

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
    fun execute(callback: Callback<String>)

    /**
     * Callback is an interface used for handling the results of an operation.
     * It provides two methods, `onSuccess` and `onError`, for handling the success
     * and error cases of the operation.
     *
     * @param T The type of the result of the operation.
     */
    interface Callback<in T> {

        /**
         * This method is called when the operation has been successful.
         * @param result The result of the operation.
         */
        fun onSuccess(result: T)

        /**
         * This method is called when the operation has failed.
         * @param throwable The exception thrown during the operation.
         */
        fun onError(throwable: Throwable)
    }
}
