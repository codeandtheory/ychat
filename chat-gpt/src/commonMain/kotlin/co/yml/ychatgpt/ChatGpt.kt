package co.yml.ychatgpt

import co.yml.ychatgpt.entrypoint.impl.ChatGptImpl
import co.yml.ychatgpt.entrypoint.model.CompletionParams

interface ChatGpt {

    suspend fun completion(input: String): String

    suspend fun completion(
        input: String,
        completionParams: CompletionParams = CompletionParams()
    ): String

    companion object {
        fun create(apiKey: String): ChatGpt {
            return ChatGptImpl(apiKey)
        }
    }
}
