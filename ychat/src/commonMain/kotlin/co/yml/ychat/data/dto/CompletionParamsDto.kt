package co.yml.ychat.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class CompletionParamsDto(
    @SerialName("model")
    val model: String,
    @SerialName("prompt")
    val prompt: String,
    @SerialName("max_tokens")
    val maxTokens: Int,
    @SerialName("temperature")
    val temperature: Double,
    @SerialName("top_p")
    val topP: Double,
    @SerialName("n")
    val completionNumber: Int = 1,
)
