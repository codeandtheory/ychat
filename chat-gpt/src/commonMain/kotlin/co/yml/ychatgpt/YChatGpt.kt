package co.yml.ychatgpt

import co.yml.ychatgpt.entrypoint.features.Completion
import co.yml.ychatgpt.entrypoint.impl.ChatGptImpl
import kotlin.jvm.JvmStatic
import kotlin.jvm.Volatile
import kotlin.native.concurrent.ThreadLocal

/**
 * YChatGPT aims to abstract all API call logic from ChatGPT for multiple platforms.
 *
 * To initialize the SDK, just call the static method ChatGpt.create(apiKey), informing the api key.
 * See [this](https://beta.openai.com/docs/api-reference/authentication) for more details on how
 * to get the api key.
 */
interface YChatGpt {

    /**
     * The completions api can be used for a wide variety of tasks. You input some text as a
     * prompt, and the model will generate a text completion that attempts to match whatever
     * context or pattern you gave it. For example, if you give the API the prompt, "As Descartes
     * said, I think, therefore", it will return the completion " I am" with high probability.
     *
     * You can configure the parameters of the completion before executing it. Example:
     * ```
     * val result = YChatGpt.create(apiKey).completion()
     *      .setInput("As Descartes said, I think, therefore")
     *      .setMaxTokens(1024)
     *      .set...
     *      .execute()
     * ```
     */
    fun completion(): Completion

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
        private var instance: YChatGpt? = null

        @JvmStatic
        fun create(apiKey: String): YChatGpt {
            return instance ?: ChatGptImpl(apiKey).also { instance = it }
        }
    }
}
