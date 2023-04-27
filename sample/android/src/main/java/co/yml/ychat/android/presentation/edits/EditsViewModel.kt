package co.yml.ychat.android.presentation.edits

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.yml.openai.provider.OpenAi
import co.yml.ychat.android.ui.components.output.OutputBoxState
import kotlinx.coroutines.launch

internal class EditsViewModel(private val openAi: OpenAi) : ViewModel() {

    val inputMessage = mutableStateOf("")

    val instructionMessage = mutableStateOf("")

    val outputBoxStates = mutableStateListOf<OutputBoxState>()

    val onEnableButton = mutableStateOf(false)

    val onEnableTextField = mutableStateOf(true)

    fun requestEdits() = viewModelScope.launch {
        outputBoxStates.clear()
        onLoading(true)
        val edits = openAi.edits()
            .setInput(inputMessage.value)
            .setResults(1)
        runCatching { edits.execute(instructionMessage.value) }
            .also { onLoading(false) }
            .onSuccess { outputBoxStates.add(OutputBoxState.Text(it.first())) }
            .onFailure { onError(true) }
    }

    fun onInputMessage(message: String) {
        this.inputMessage.value = message
        onEnableButton()
    }

    fun onInstructionMessage(message: String) {
        this.instructionMessage.value = message
        onEnableButton()
    }

    private fun onEnableButton() {
        onEnableButton.value = inputMessage.value.isNotEmpty() &&
                instructionMessage.value.isNotEmpty()
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
            onEnableButton()
        }
    }

    private fun onError(isError: Boolean) {
        if (isError) {
            outputBoxStates.add(OutputBoxState.Error)
        } else {
            outputBoxStates.remove(OutputBoxState.Error)
        }
    }
}