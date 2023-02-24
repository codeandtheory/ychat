package co.yml.ychat.entrypoint.features

import co.yml.ychat.YChat
import co.yml.ychat.data.exception.ChatGptException
import kotlin.coroutines.cancellation.CancellationException

/**
 * The completions api can be used for a wide variety of tasks. You input some text as a
 * prompt, and the model will generate a text completion that attempts to match whatever
 * context or pattern you gave it. For example, if you give the API the prompt, "As Descartes
 * said, I think, therefore", it will return the completion " I am" with high probability.
 */
interface Completion {

    /**
     * The default value of [input] is "".
     * @param input The prompt(s) to generate completions for.
     */
    fun setInput(input: String): Completion

    /**
     * The default value of [model] is "text-davinci-003".
     * @param model ID of the model to use.
     */
    fun setModel(model: String): Completion

    /**
     * The default value of [tokens] is 1024.
     * @param tokens The maximum number of tokens to generate in the completion. The token count
     * of your prompt plus max_tokens cannot exceed the model's context length. Most models have
     * a context length of 2048 tokens (except for the newest models, which support 4096).
     */
    fun setMaxTokens(tokens: Int): Completion

    /**
     * The default value of [temperature] is 1.0.
     * @param temperature What sampling temperature to use. Higher values means the model will take more
     * risks. Try 0.9 for more creative applications, and 0 (argmax sampling) for ones with a
     * well-defined answer. We generally recommend altering this or [setTopP] but not both.
     */
    fun setTemperature(temperature: Double): Completion

    /**
     * The default value of [topP] is 1.0.
     * @param topP An alternative to sampling with temperature, called nucleus sampling, where the model
     * considers the results of the tokens with top_p probability mass. So 0.1 means only the tokens
     * comprising the top 10% probability mass are considered. We generally recommend altering
     * this or [setTemperature] but not both.
     */
    fun setTopP(topP: Double): Completion

    /**
     * The default value of [isSaveHistory] is false.
     * @param isSaveHistory This flag enables the chat history to be stored, so that the GPT chat
     * can get the context of previous conversations.
     */
    fun saveHistory(isSaveHistory: Boolean): Completion

    /**
     * Execute completion request.
     * @return one predicted completion of the given prompt.
     */
    @Throws(CancellationException::class, ChatGptException::class)
    suspend fun execute(): String

    /**
     * Executes completion request and returns the result through a callback.
     * @param callback a callback to return the result of the completion.
     */
    fun execute(callback: YChat.Callback<String>)
}
