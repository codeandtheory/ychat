package co.yml.ducai.provider

import co.yml.ducai.provider.entrypoint.DucAIImpl
import co.yml.ducai.provider.entrypoint.features.DucAICompletions
import kotlin.jvm.JvmStatic
import kotlin.jvm.Volatile
import kotlin.native.concurrent.ThreadLocal

interface DucAI {

    fun completion(): DucAICompletions

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

    @ThreadLocal
    companion object {

        @Volatile
        private var instance: DucAI? = null

        @JvmStatic
        fun create(): DucAI {
            return instance ?: DucAIImpl().also { instance = it }
        }
    }
}
