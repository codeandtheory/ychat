package co.yml.ychat.android.presentation.edits

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import co.yml.openai.provider.OpenAI
import co.yml.ychat.android.BuildConfig
import co.yml.ychat.android.R
import co.yml.ychat.android.ui.components.button.ButtonContained
import co.yml.ychat.android.ui.components.output.OutputBox
import co.yml.ychat.android.ui.components.textfield.StandardTextField
import co.yml.ychat.android.ui.theme.Dimens
import co.yml.ychat.android.ui.theme.TypographyStyle
import co.yml.ychat.android.ui.theme.YChatTheme
import org.koin.androidx.compose.getViewModel

@Composable
internal fun EditsScreen(viewModel: EditsViewModel = getViewModel()) {
    Column(
        modifier = Modifier
            .background(YChatTheme.colors.background)
            .padding(Dimens.MD)
            .fillMaxSize(),
    ) {
        TypographyStyle.MediumBody.Text(text = stringResource(id = R.string.edits_input_title))
        StandardTextField(
            value = viewModel.inputMessage.value,
            hint = stringResource(id = R.string.edits_input_hint),
            modifier = Modifier
                .height(100.dp)
                .fillMaxWidth()
                .padding(top = Dimens.SM, bottom = Dimens.MD),
            onTextChanged = { viewModel.onInputMessage(it) },
            enabled = viewModel.onEnableTextField.value,
        )
        TypographyStyle.MediumBody.Text(text = stringResource(id = R.string.edits_instruction_title))
        StandardTextField(
            value = viewModel.instructionMessage.value,
            hint = stringResource(id = R.string.edits_instruction_hint),
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = Dimens.SM, bottom = Dimens.XM)
            ,
            onTextChanged = { viewModel.onInstructionMessage(it) },
            enabled = viewModel.onEnableTextField.value,
        )
        ButtonContained(
            text = stringResource(id = R.string.edits_action),
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = Dimens.XM),
            enabled = viewModel.onEnableButton.value,
            onClick = { viewModel.requestEdits() }
        )
        OutputBox(outputBoxStates = viewModel.outputBoxStates)
    }
}

@Preview
@Composable
private fun EditsScreenPreview() {
    YChatTheme {
        val openAi = OpenAI.create(BuildConfig.API_KEY)
        val viewModel = EditsViewModel(openAi)
        EditsScreen(viewModel)
    }
}
