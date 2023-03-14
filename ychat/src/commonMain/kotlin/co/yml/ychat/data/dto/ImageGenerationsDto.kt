package co.yml.ychat.data.dto

import co.yml.ychat.domain.model.ImageGeneratedDto
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class ImageGenerationsDto(
    @SerialName("created")
    val created: Long,
    @SerialName("data")
    val data: List<ImageGeneratedDto>,
)
