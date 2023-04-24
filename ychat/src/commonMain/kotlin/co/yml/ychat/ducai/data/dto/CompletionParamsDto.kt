package co.yml.ychat.ducai.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class CompletionParamsDto(
    @SerialName("data")
    val data: String
)
