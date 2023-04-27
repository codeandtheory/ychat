package co.yml.openai.provider.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class EditsDto(
    @SerialName("object")
    val objectType: String,
    @SerialName("created")
    val created: Long,
    @SerialName("choices")
    val choices: List<EditsChoiceDto>,
    @SerialName("usage")
    val usage: UsageDto,
)
