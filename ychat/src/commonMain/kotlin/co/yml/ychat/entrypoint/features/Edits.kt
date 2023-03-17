package co.yml.ychat.entrypoint.features

import co.yml.ychat.YChat
import co.yml.ychat.data.exception.ChatGptException
import kotlin.coroutines.cancellation.CancellationException

interface Edits {

    fun setInput(input: String): Edits

    fun setResults(results: Int): Edits

    fun setModel(model: String): Edits

    fun setTemperature(temperature: Double): Edits

    fun setTopP(topP: Double): Edits

    @Throws(CancellationException::class, ChatGptException::class)
    suspend fun execute(instruction: String): List<String>

    fun execute(instruction: String, callback: YChat.Callback<List<String>>)
}
