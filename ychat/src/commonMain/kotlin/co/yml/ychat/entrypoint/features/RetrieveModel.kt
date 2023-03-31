package co.yml.ychat.entrypoint.features

import co.yml.ychat.YChat
import co.yml.ychat.data.exception.ChatGptException
import co.yml.ychat.domain.model.AIModel
import kotlin.coroutines.cancellation.CancellationException

interface RetrieveModel {

    /**
     * Retrieves an artificial intelligence model based on the given [id] and provides basic
     * information about it, such as the owner and availability.
     *
     * @param id The ID of the model to retrieve.
     * @return The artificial intelligence model with the given [id].
     * @throws CancellationException if the operation is cancelled.
     * @throws ChatGptException if there is an error generating chat completions.
     */
    @Throws(CancellationException::class, ChatGptException::class)
    suspend fun execute(id: String): AIModel

    /**
     * Retrieves an artificial intelligence model based on the given [id], and passes it to
     * the provided callback.
     *
     * @param callback The callback to receive the model.
     */
    fun execute(id: String, callback: YChat.Callback<AIModel>)
}
