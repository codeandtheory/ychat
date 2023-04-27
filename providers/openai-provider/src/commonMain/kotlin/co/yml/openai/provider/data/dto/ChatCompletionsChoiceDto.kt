package co.yml.openai.provider.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class ChatCompletionsChoiceDto(
    @SerialName("index")
    val index: Int,
    @SerialName("message")
    val message: ChatMessageDto,
    @SerialName("finish_reason")
    val finishReason: String?,
)
