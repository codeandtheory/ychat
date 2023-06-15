package co.yml.ychat.android.presentation.chatcompletions.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.yml.openai.provider.OpenAI
import co.yml.ychat.android.presentation.chatcompletions.model.MessageType
import kotlinx.coroutines.launch

internal class ChatCompletionsViewModel(private val openAi: OpenAI) : ViewModel() {

    val message = mutableStateOf("")

    val messages = mutableStateListOf<MessageType>()

    val onEnableButton = mutableStateOf(message.value.isNotEmpty())

    val onEnableTextField = mutableStateOf(true)

    private val chatCompletions by lazy {
        openAi.chatCompletions()
            .addMessage("assistant", "You are helpful assistant")
            .setMaxTokens(MAX_TOKENS)
    }

    fun sendMessage() = viewModelScope.launch {
        val messageToSend = message.value
        messages.add(MessageType.Sender(message.value))
        onLoading(true)
        onMessage("")
        runCatching { chatCompletions.execute(messageToSend) }
            .also { onLoading(false) }
            .onSuccess { messages.add(MessageType.Bot(it.first().content)) }
            .onFailure { onError(true) }
    }

    fun onMessage(message: String) {
        this.message.value = message
        onEnableButton.value = message.isNotEmpty()
    }

    private fun onLoading(isLoading: Boolean) {
        if (isLoading) {
            onError(false)
            onEnableButton.value = false
            onEnableTextField.value = false
            messages.add(MessageType.Loading)
        } else {
            onEnableTextField.value = true
            messages.remove(MessageType.Loading)
        }
    }

    private fun onError(isError: Boolean) {
        if (isError) {
            val error = messages.removeLast() as? MessageType.Sender ?: return
            error.isError = true
            messages.add(error)
        } else {
            messages.removeIf { it is MessageType.Sender && it.isError }
        }
    }

    companion object {
        private const val MAX_TOKENS = 1024
    }
}
