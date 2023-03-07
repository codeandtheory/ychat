package co.yml.ychat.entrypoint.features

import co.yml.ychat.YChat
import co.yml.ychat.data.exception.ChatGptException
import co.yml.ychat.domain.model.ChatMessage
import kotlin.coroutines.cancellation.CancellationException

interface ChatCompletions {

    /**
     * Sets the ID of the [model] to use. The default value is "gpt-3.5-turbo".
     *
     * @param model ID of the model to use.
     * @return The updated [ChatCompletions] object with the new model ID.
     */
    fun setModel(model: String): ChatCompletions

    /**
     * Sets the [topP] value for nucleus sampling. The default value is 1.0.
     *
     * @param topP An alternative to sampling with temperature, called nucleus sampling, where the model
     * considers the results of the tokens with top_p probability mass. So 0.1 means only the tokens
     * comprising the top 10% probability mass are considered. We generally recommend altering
     * this or [setTemperature] but not both.
     * @return The updated [ChatCompletions] object with the new top_p value.
     */
    fun setTopP(topP: Double): ChatCompletions

    /**
     * Sets the sampling [temperature] to use. The default value is 1.0.
     *
     * @param temperature What sampling temperature to use. Higher values means the model will take more
     * risks. Try 0.9 for more creative applications, and 0 (argmax sampling) for ones with a
     * well-defined answer. We generally recommend altering this or [setTopP] but not both.
     * @return The updated [ChatCompletions] object with the new sampling temperature.
     */
    fun setTemperature(temperature: Double): ChatCompletions

    /**
     * Sets the number of chat completion choices to generate for each input message.
     * The default value is 1.
     *
     * @param results How many chat completion choices to generate for each input message.
     * @return The updated [ChatCompletions] object with the new number of results.
     */
    fun setMaxResults(results: Int): ChatCompletions

    /**
     * The default value of [tokens] is 4096.
     *
     * @param tokens The maximum number of tokens allowed for the generated answer.
     * @return The updated [ChatCompletions] object with the new maximum number of tokens.
     */
    fun setMaxTokens(tokens: Int): ChatCompletions

    /**
     * Adds a new message to the ongoing conversation with the given [role] and [content].
     *
     * @param role The role of the speaker who sends the message (either “system”, “user” or “assistant”).
     * @param content The content of the message sent by the speaker.
     * @return The updated [ChatCompletions] object with the newly added message.
     */
    fun addMessage(role: String, content: String): ChatCompletions

    /**
     * Generates a list of chat completions for the given user [content].
     *
     * @param content The content of the user input.
     * @return A list of chat message objects representing the possible completions.
     * @throws CancellationException if the operation is cancelled.
     * @throws ChatGptException if there is an error generating chat completions.
     */
    @Throws(CancellationException::class, ChatGptException::class)
    suspend fun execute(content: String): List<ChatMessage>

    /**
     * Generates a list of chat completions for the given [content] message and passes it to the
     * provided callback.
     *
     * @param content The content of the user input.
     * @param callback The callback to receive the list of possible completions.
     */
    fun execute(content: String, callback: YChat.Callback<List<ChatMessage>>)
}
