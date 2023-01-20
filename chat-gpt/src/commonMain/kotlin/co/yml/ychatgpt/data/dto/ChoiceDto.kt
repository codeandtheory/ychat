package co.yml.ychatgpt.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class ChoiceDto(
    @SerialName("text")
    val text: String,
    @SerialName("index")
    val index: Int,
    @SerialName("logprobs")
    val logProbs: Int?,
    @SerialName("finish_reason")
    val finishReason: String,
)
