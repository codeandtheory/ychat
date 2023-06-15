package co.yml.openai.provider.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class EditsChoiceDto(
    @SerialName("text")
    val text: String,
    @SerialName("index")
    val index: Int
)
