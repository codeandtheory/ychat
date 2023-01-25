package co.yml.ychatgpt

import co.yml.ychatgpt.data.exception.ChatGptException
import co.yml.ychatgpt.entrypoint.impl.ChatGptImpl
import co.yml.ychatgpt.entrypoint.model.CompletionParams
import kotlin.coroutines.cancellation.CancellationException
import kotlin.jvm.Synchronized
import kotlin.jvm.Volatile
import kotlin.native.concurrent.ThreadLocal

/**
 * YChatGPT aims to abstract all API call logic from ChatGPT for multiple platforms.
 *
 * To initialize the SDK, just call the static method ChatGpt.create(apiKey), informing the api key.
 * See [this](https://beta.openai.com/docs/api-reference/authentication) for more details on how
 * to get the api key.
 */
interface ChatGpt {

    /**
     * The completions api can be used for a wide variety of tasks. You [input] some text as a
     * prompt, and the model will generate a text completion that attempts to match whatever
     * context or pattern you gave it. For example, if you give the API the prompt, "As Descartes
     * said, I think, therefore", it will return the completion " I am" with high probability.
     *
     * @param input The prompt(s) to generate completions for.
     */
    @Throws(CancellationException::class, ChatGptException::class)
    suspend fun completion(input: String): String

    /**
     * The completions api can be used for a wide variety of tasks. You [input] some text as a
     * prompt, and the model will generate a text completion that attempts to match whatever
     * context or pattern you gave it. For example, if you give the API the prompt, "As Descartes
     * said, I think, therefore", it will return the completion " I am" with high probability.
     *
     * You can configure the behavior of the completion editing the [completionParams].
     *
     * @param input The prompt(s) to generate completions for.
     * @param completionParams Params to set the completion behaviour.
     */
    @Throws(CancellationException::class, ChatGptException::class)
    suspend fun completion(
        input: String,
        completionParams: CompletionParams = CompletionParams()
    ): String

    @ThreadLocal
    companion object {

        @Volatile
        private var instance: ChatGpt? = null

        @Synchronized
        fun create(apiKey: String): ChatGpt {
            return instance ?: ChatGptImpl(apiKey).also { instance = it }
        }
    }
}
