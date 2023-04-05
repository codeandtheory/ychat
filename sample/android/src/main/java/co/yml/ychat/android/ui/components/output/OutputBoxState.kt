package co.yml.ychat.android.ui.components.output

internal sealed class OutputBoxState {
    data class Text(val text: String, val isMarked: Boolean = false) : OutputBoxState()
    object Error : OutputBoxState()
    object Loading : OutputBoxState()
}
