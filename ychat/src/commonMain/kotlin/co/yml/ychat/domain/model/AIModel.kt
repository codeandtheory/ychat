package co.yml.ychat.domain.model

/**
 * The artificial intelligence model, providing basic information about the model such as the
 * owner and permission.
 * @property id The ID of the model.
 * @property ownedBy The user or organization that owns the model.
 * @property permission A list of permissions associated with the model.
 */
data class AIModel(
    val id: String,
    val ownedBy: String,
    val permission: List<AIModelPermission>,
)
