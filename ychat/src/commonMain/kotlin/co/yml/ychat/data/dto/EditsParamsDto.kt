package co.yml.ychat.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class EditsParamsDto(
    @SerialName("model")
    val model: String,
    @SerialName("input")
    val input: String,
    @SerialName("instruction")
    val instruction: String,
    @SerialName("n")
    val results: Int = 1,
    @SerialName("temperature")
    val temperature: Double,
    @SerialName("top_p")
    val topP: Double,
)
