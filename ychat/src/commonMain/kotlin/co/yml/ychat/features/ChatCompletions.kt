package co.yml.ychat.features

import co.yml.ychat.core.exceptions.YChatException
import kotlin.coroutines.cancellation.CancellationException

interface ChatCompletions {

    @Throws(CancellationException::class, YChatException::class)
    suspend fun execute(input: String): String

    fun execute(input: String, callback: Callback<String>)

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
