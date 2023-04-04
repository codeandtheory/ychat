package co.yml.ychat.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class AudioResultDto(
    @SerialName("text")
    val text: String,
)
