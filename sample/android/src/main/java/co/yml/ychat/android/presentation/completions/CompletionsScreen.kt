package co.yml.ychat.android.presentation.completions

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import co.yml.openai.provider.OpenAi
import co.yml.ychat.YChat
import co.yml.ychat.android.BuildConfig
import co.yml.ychat.android.R
import co.yml.ychat.android.ui.components.button.ButtonContained
import co.yml.ychat.android.ui.components.output.OutputBox
import co.yml.ychat.android.ui.components.textfield.StandardTextField
import co.yml.ychat.android.ui.theme.Dimens
import co.yml.ychat.android.ui.theme.YChatTheme
import org.koin.androidx.compose.getViewModel

@Composable
internal fun CompletionsScreen(viewModel: CompletionsViewModel = getViewModel()) {
    Column(
        modifier = Modifier
            .background(YChatTheme.colors.background)
            .padding(Dimens.MD)
            .fillMaxHeight(),
    ) {
        StandardTextField(
            value = viewModel.message.value,
            hint = stringResource(id = R.string.completions_hint),
            modifier = Modifier.fillMaxWidth(),
            onTextChanged = { viewModel.onMessage(it) },
            enabled = viewModel.onEnableTextField.value,
        )
        ButtonContained(
            text = stringResource(id = R.string.completions_action),
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = Dimens.MD, bottom = Dimens.XM),
            enabled = viewModel.onEnableButton.value,
            onClick = { viewModel.requestCompletions() }
        )
        OutputBox(outputBoxStates = viewModel.outputBoxStates)
    }
}

@Preview
@Composable
private fun CompletionsScreenPreview() {
    YChatTheme {
        val openAi = OpenAi.create(BuildConfig.API_KEY)
        val viewModel = CompletionsViewModel(openAi)
        CompletionsScreen(viewModel)
    }
}
