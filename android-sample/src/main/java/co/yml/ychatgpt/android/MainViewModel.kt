package co.yml.ychatgpt.android

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.yml.ychatgpt.YChatGpt
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainViewModel(private val chatGpt: YChatGpt) : ViewModel() {

    private val _items = mutableStateListOf<MessageItem>()
    val items = _items

    private val _isLoading = MutableLiveData(false)
    val isLoading get() = _isLoading

    private var typingTxt = mutableStateOf("")
    private var typingItem = mutableStateOf(MessageItem(message = typingTxt.value, isOut = false))

    private fun setLoading(isLoading: Boolean) {
        _isLoading.value = isLoading
    }

    fun onSendMessage(message: String, typingStr: String) {
        updateTypingMessage(typingStr)
        viewModelScope.launch {
            showTypingAnimation(message)
            writeResponse(requestCompletion(message))
        }
    }

    private suspend fun showTypingAnimation(message: String) {
        items.add(MessageItem(message = message, isOut = true))
        delay((1000..2000).random().toLong())
        setLoading(true)
        items.add(typingItem.value)
    }

    private fun writeResponse(chatGptAnswer: String) {
        items.remove(items[items.lastIndex])
        items.add(MessageItem(message = chatGptAnswer, isOut = false))
        setLoading(false)
    }

    private suspend fun requestCompletion(message: String): String {
        return try {
            chatGpt.completion()
                .setInput(message)
                .saveHistory(false)
                .setMaxTokens(MAX_TOKENS)
                .execute()
        } catch (e: Exception) {
            e.message ?: ERROR
        }
    }

    fun updateTyping(string: String) {
        typingTxt.value = if (typingTxt.value.endsWith("...")) string else "${typingTxt.value}."
        items[items.lastIndex] = items.last().copy(message = typingTxt.value)
    }

    private fun updateTypingMessage(typingStr: String) {
        typingTxt.value = typingStr
        typingItem.value = MessageItem(message = typingTxt.value, isOut = false)
    }

    companion object {
        private const val ERROR = "Error"
        private const val MAX_TOKENS = 1024
    }
}