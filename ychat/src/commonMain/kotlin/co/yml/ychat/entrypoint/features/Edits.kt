package co.yml.ychat.entrypoint.features

import co.yml.ychat.YChat
import co.yml.ychat.core.exceptions.YChatException
import kotlin.coroutines.cancellation.CancellationException

interface Edits {

    /**
     * Sets the input to generate edits for.
     *
     * @param input The input text to generate edits for.
     * @return The updated [Edits] object with the new input text.
     */
    fun setInput(input: String): Edits

    /**
     * Sets the number of edit results to generate. The default value is 1.
     *
     * @param results The number of edit results to generate.
     * @return The updated [Edits] object with the new number of results.
     */
    fun setResults(results: Int): Edits

    /**
     * Sets the ID of the model to use. The default value is "text-davinci-edit-001".
     *
     * @param model ID of the model to use.
     * @return The updated [Edits] object with the new model ID.
     */
    fun setModel(model: String): Edits

    /**
     * Sets the sampling [temperature] to use. The default value is 1.0.
     *
     * @param temperature What sampling temperature to use. Higher values means the model will
     * take more risks. Try 0.9 for more creative applications, and 0 (argmax sampling)
     * for ones with a well-defined answer.
     * @return The updated [Edits] object with the new sampling temperature.
     */
    fun setTemperature(temperature: Double): Edits

    /**
     * Sets the [topP] value for nucleus sampling. The default value is 1.0.
     *
     * @param topP An alternative to sampling with temperature, called nucleus sampling,
     * where the model considers the results of the tokens with top_p probability mass.
     * So 0.1 means only the tokens comprising the top 10% probability mass are considered.
     * @return The updated [Edits] object with the new top_p value.
     */
    fun setTopP(topP: Double): Edits

    /**
     * Generates a list of possible edits for the given instruction.
     *
     * @param instruction The instruction to generate edits for.
     * @return A list of edits generated for the given instruction.
     * @throws CancellationException if the operation is cancelled.
     * @throws YChatException if there is an error generating edits.
     */
    @Throws(CancellationException::class, YChatException::class)
    suspend fun execute(instruction: String): List<String>

    /**
     * Generates a list of possible edits for the given instruction and passes it to the
     * provided callback.
     *
     * @param instruction The instruction to generate edits for.
     * @param callback The callback to receive the list of possible edits.
     */
    fun execute(instruction: String, callback: YChat.Callback<List<String>>)
}
