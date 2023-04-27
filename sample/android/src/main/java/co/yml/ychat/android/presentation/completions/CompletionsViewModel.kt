package co.yml.ychat.android.presentation.completions

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.yml.openai.provider.OpenAi
import co.yml.ychat.android.ui.components.output.OutputBoxState
import kotlinx.coroutines.launch

internal class CompletionsViewModel(private val openAi: OpenAi): ViewModel() {

    val message = mutableStateOf("")

    val outputBoxStates = mutableStateListOf<OutputBoxState>()

    val onEnableButton = mutableStateOf(message.value.isNotEmpty())

    val onEnableTextField = mutableStateOf(true)

    fun requestCompletions() = viewModelScope.launch {
        val messageToSend = message.value
        outputBoxStates.clear()
        outputBoxStates.add(OutputBoxState.Text(messageToSend))
        onLoading(true)
        onMessage("")
        val completions = openAi.completion()
            .setMaxTokens(MAX_TOKENS)
            .setInput(messageToSend)
        runCatching { completions.execute() }
            .also { onLoading(false) }
            .onSuccess { outputBoxStates.add(OutputBoxState.Text(it, true)) }
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
            outputBoxStates.add(OutputBoxState.Loading)
        } else {
            onEnableTextField.value = true
            outputBoxStates.remove(OutputBoxState.Loading)
        }
    }

    private fun onError(isError: Boolean) {
        if (isError) {
            outputBoxStates.add(OutputBoxState.Error)
        } else {
            outputBoxStates.remove(OutputBoxState.Error)
        }
    }

    companion object {
        private const val MAX_TOKENS = 240
    }
}
