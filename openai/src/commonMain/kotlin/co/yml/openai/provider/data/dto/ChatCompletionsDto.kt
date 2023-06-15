package co.yml.openai.provider.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class ChatCompletionsDto(
    @SerialName("id")
    val id: String,
    @SerialName("model")
    val model: String,
    @SerialName("choices")
    val choices: List<ChatCompletionsChoiceDto>,
    @SerialName("usage")
    val usage: UsageDto,
)
