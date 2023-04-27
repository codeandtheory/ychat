package co.yml.ychat.android.presentation.images

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.yml.openai.provider.OpenAi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

internal class ImagesViewModel(private val openAi: OpenAi): ViewModel() {

    val inputMessage = mutableStateOf("")

    val onEnableButton = mutableStateOf(false)

    private val _state = MutableStateFlow<State>(State.Idle)
    val state: StateFlow<State> = _state.asStateFlow()

    private val imageGenerations by lazy { openAi.imageGenerations() }

    fun onInputMessage(message: String) {
        this.inputMessage.value = message
        onEnableButton.value = inputMessage.value.isNotEmpty()
    }

    fun requestEdits() = viewModelScope.launch {
        _state.value = State.Loading
        onEnableButton.value = false
        runCatching { imageGenerations.execute(inputMessage.value) }
            .also { onEnableButton.value = true }
            .onSuccess { _state.value = State.Success(it.first()) }
            .onFailure { _state.value = State.Error }
    }

    sealed class State {
        object Idle: State()
        object Loading : State()
        data class Success(val generatedImage: String) : State()
        object Error : State()
    }
}
