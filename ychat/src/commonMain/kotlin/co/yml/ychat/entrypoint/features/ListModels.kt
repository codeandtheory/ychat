package co.yml.ychat.entrypoint.features

import co.yml.ychat.YChat
import co.yml.ychat.core.exceptions.YChatException
import co.yml.ychat.domain.model.AIModel
import kotlin.coroutines.cancellation.CancellationException

interface ListModels {

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
    fun execute(callback: YChat.Callback<List<AIModel>>)
}
