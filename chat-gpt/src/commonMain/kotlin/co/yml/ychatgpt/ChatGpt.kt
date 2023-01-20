package co.yml.ychatgpt

import co.yml.ychatgpt.entrypoint.ChatGptImpl
import co.yml.ychatgpt.entrypoint.CompletionParams

interface ChatGpt {

    suspend fun completion(input: String): String

    suspend fun completion(input: String, completionParams: CompletionParams): String

    companion object {
        fun create(apiKey: String): ChatGpt {
            return ChatGptImpl(apiKey)
        }
    }
}