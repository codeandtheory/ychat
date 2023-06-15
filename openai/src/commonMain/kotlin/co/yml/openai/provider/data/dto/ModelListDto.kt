package co.yml.openai.provider.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class ModelListDto(
    @SerialName("data")
    val models: List<ModelDto>,
)
