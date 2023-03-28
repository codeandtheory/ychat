package co.yml.ychat.android.presentation.chatcompletions.model

internal sealed class MessageType {
    data class Sender(val text: String, var isError: Boolean = false): MessageType()
    data class Bot(val text: String): MessageType()
    object Loading: MessageType()
}
