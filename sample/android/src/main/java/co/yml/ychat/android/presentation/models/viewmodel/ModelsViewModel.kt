package co.yml.ychat.android.presentation.models.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.yml.openai.provider.OpenAi
import co.yml.openai.provider.domain.model.AIModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

internal class ModelsViewModel(private val openAi: OpenAi) : ViewModel() {

    private val listModels by lazy { openAi.listModels() }

    private val _state = MutableStateFlow<State>(State.Loading)
    val state: StateFlow<State> = _state.asStateFlow()

    init {
        fetchModels()
    }

    fun fetchModels() = viewModelScope.launch {
        _state.value = State.Loading
        runCatching { listModels.execute() }
            .onSuccess { _state.value = State.Success(it) }
            .onFailure { _state.value = State.Error }
    }

    sealed class State {
        object Loading : State()
        data class Success(val models: List<AIModel>) : State()
        object Error : State()
    }
}
