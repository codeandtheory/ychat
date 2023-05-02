package co.yml.ychat.entrypoint.features

import co.yml.ychat.YChat
import co.yml.ychat.core.exceptions.YChatException
import kotlin.coroutines.cancellation.CancellationException

interface ImageGenerations {

    /**
     * Sets the number of image generation results to return. The default value is 1.
     * @param results The number of image generation results to return.
     * @return The updated [ImageGenerations] object with the new number of results.
     */
    fun setResults(results: Int): ImageGenerations

    /**
     * Sets the size of the images generated (squared). Ex. 256x256, 512x512, 1024x1024.
     * The default value is "256x256".
     * @param size The size of the generated images.
     * @return The updated [ImageGenerations] object with the new image size.
     */
    fun setSize(size: String): ImageGenerations

    /**
     * Sets the format in which the generated images are returned. Must be one of url or b64_json.
     * The default value is "url".
     * @param responseFormat The response format of the generated images.
     * @return The updated [ImageGenerations] object with the new response format.
     */
    fun setResponseFormat(responseFormat: String): ImageGenerations

    /**
     * Generates a list of image generations for the given [prompt].
     * @param prompt The prompt for generating the images.
     * @return A list of generated image objects.
     * @throws CancellationException if the operation is cancelled.
     * @throws YChatException if there is an error generating images.
     */
    @Throws(CancellationException::class, YChatException::class)
    suspend fun execute(prompt: String): List<String>

    /**
     * Generates a list of image generations for the given [prompt] and passes it to
     * the provided [callback].
     * @param prompt The prompt for generating the images.
     * @param callback The callback to receive the list of generated images.
     */
    fun execute(prompt: String, callback: YChat.Callback<List<String>>)
}
