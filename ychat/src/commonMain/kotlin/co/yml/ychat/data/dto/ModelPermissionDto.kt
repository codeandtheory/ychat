package co.yml.ychat.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class ModelPermissionDto(
    @SerialName("id")
    val id: String,
    @SerialName("allow_create_engine")
    val allowCreateEngine: Boolean,
    @SerialName("allow_sampling")
    val allowSampling: Boolean,
    @SerialName("allow_logprobs")
    val allowLogProbs: Boolean,
    @SerialName("allow_search_indices")
    val allowSearchIndices: Boolean,
    @SerialName("allow_view")
    val allowView: Boolean,
    @SerialName("allow_fine_tuning")
    val allowFineTuning: Boolean,
    @SerialName("organization")
    val organization: String,
    @SerialName("is_blocking")
    val isBlocking: Boolean,
)
