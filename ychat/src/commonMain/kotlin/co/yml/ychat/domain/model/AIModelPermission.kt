package co.yml.ychat.domain.model

/**
 * A representation of the permissions associated with a [AIModel].
 * @property id The ID of the permission.
 * @property allowCreateEngine Whether the permission allows creating engines.
 * @property allowSampling Whether the permission allows sampling from the model.
 * @property allowLogProbs Whether the permission allows accessing log probabilities.
 * @property allowSearchIndices Whether the permission allows accessing search indices.
 * @property allowView Whether the permission allows viewing the model.
 * @property allowFineTuning Whether the permission allows fine-tuning the model.
 * @property organization The organization associated with the permission.
 * @property isBlocking Whether the permission is currently blocking access to the model.
 */
data class AIModelPermission(
    val id: String,
    val allowCreateEngine: Boolean,
    val allowSampling: Boolean,
    val allowLogProbs: Boolean,
    val allowSearchIndices: Boolean,
    val allowView: Boolean,
    val allowFineTuning: Boolean,
    val organization: String,
    val isBlocking: Boolean,
)
