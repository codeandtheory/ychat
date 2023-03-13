package co.yml.ychat.domain.model

/**
 * Represents a message in a conversation, consisting of a [role] indicating the speaker
 * (e.g., “system”, “user” or “assistant”), and the [content] of the message sent by the speaker.
 * @property role The role of the speaker who sends the message.
 * @property content The content of the message sent by the speaker.
 */
data class ChatMessage(
    val role: String,
    val content: String
)
