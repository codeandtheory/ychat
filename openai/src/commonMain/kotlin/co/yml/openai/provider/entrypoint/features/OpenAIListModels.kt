package co.yml.openai.provider.entrypoint.features

import co.yml.openai.provider.OpenAI
import co.yml.ychat.core.exceptions.YChatException
import co.yml.openai.provider.domain.model.AIModel
import kotlin.coroutines.cancellation.CancellationException

interface OpenAIListModels {

    /**
     * Retrieve a list of currently available models, and provides basic information about
     * each one such as the owner and availability.
     *
     * @return A list of artificial intelligence models.
     * @throws CancellationException if the operation is cancelled.
     * @throws YChatException if there is an error generating chat completions.
     */
    @Throws(CancellationException::class, YChatException::class)
    suspend fun execute(): List<AIModel>

    /**
     * Retrieve a list of currently available models, and passes it to the provided callback.
     *
     * @param callback The callback to receive the list of available models.
     */
    fun execute(callback: OpenAI.Callback<List<AIModel>>)
}
