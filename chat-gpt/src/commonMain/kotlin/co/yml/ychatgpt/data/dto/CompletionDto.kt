package co.yml.ychatgpt.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class CompletionDto(
    @SerialName("id")
    val id: String,
    @SerialName("model")
    val model: String,
    @SerialName("choices")
    val choices: List<ChoiceDto>,
    @SerialName("usage")
    val usage: UsageDto,
)
