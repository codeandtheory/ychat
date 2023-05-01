package co.yml.ychat.android.presentation.completions

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.yml.ducai.provider.DucAI
import co.yml.openai.provider.OpenAi
import co.yml.ychat.android.ui.components.output.OutputBoxState
import co.yml.ychat.android.usecases.GetSelectedProviderUseCase
import co.yml.ychat.core.provider.CoreProvider
import kotlinx.coroutines.flow.flatMapMerge
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

internal class CompletionsViewModel(private val getSelectedProviderUseCase: GetSelectedProviderUseCase) :
    ViewModel() {

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
        getSelectedProviderUseCase().map { provider: CoreProvider ->
            runCatching {
                when (provider) {
                    is OpenAi -> {
                        provider.completion().setMaxTokens(MAX_TOKENS).setInput(messageToSend)
                            .execute()
                    }

                    is DucAI -> {
                        provider.completion().setInput(messageToSend).execute()
                    }

                    else -> error("Provider not found")
                }
            }.also { onLoading(false) }
        }.flatMapMerge { result ->
            return@flatMapMerge flow<Result<String>> {
                emit(result)
            }
        }.collect {
            it.onSuccess {
                outputBoxStates.add(OutputBoxState.Text(it, true))
            }.onFailure {
                Log.e(TAG, it.message, it)
                onError(true)
            }
        }

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
        val TAG = CompletionsViewModel::class.java.simpleName
        private const val MAX_TOKENS = 240
    }
}
