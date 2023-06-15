package co.yml.openai.provider.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class ModelDto(
    @SerialName("id")
    val id: String,
    @SerialName("owned_by")
    val ownedBy: String,
    @SerialName("permission")
    val permission: List<ModelPermissionDto>,
)
