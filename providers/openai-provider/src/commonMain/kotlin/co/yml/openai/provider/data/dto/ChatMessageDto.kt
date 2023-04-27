package co.yml.openai.provider.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class ChatMessageDto(
    @SerialName("role")
    val role: String,
    @SerialName("content")
    val content: String,
)
