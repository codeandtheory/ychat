package co.yml.ychat.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class ChatCompletionParamsDto(
    @SerialName("model")
    val model: String,
    @SerialName("messages")
    val messages: List<ChatMessageDto>,
    @SerialName("max_tokens")
    val maxTokens: Int,
    @SerialName("temperature")
    val temperature: Double,
    @SerialName("top_p")
    val topP: Double,
    @SerialName("n")
    val maxResults: Int = 1,
)
