package co.yml.ducai.provider.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class DucAiCompletionDto(
    @SerialName("data")
    val data: List<String>
)
