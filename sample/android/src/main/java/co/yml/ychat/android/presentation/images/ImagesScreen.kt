package co.yml.ychat.android.presentation.images

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import co.yml.openai.provider.OpenAI
import co.yml.ychat.android.BuildConfig
import co.yml.ychat.android.R
import co.yml.ychat.android.presentation.images.ImagesViewModel.State
import co.yml.ychat.android.ui.components.button.ButtonContained
import co.yml.ychat.android.ui.components.feedback.Feedback
import co.yml.ychat.android.ui.components.feedback.model.FeedbackState
import co.yml.ychat.android.ui.components.placeholder.ImagePlaceHolder
import co.yml.ychat.android.ui.components.textfield.StandardTextField
import co.yml.ychat.android.ui.theme.Dimens
import co.yml.ychat.android.ui.theme.TypographyStyle
import co.yml.ychat.android.ui.theme.YChatTheme
import coil.compose.SubcomposeAsyncImage
import org.koin.androidx.compose.getViewModel

@Composable
internal fun ImagesScreen(viewModel: ImagesViewModel = getViewModel()) {
    val state = viewModel.state.collectAsState().value
    Column(
        modifier = Modifier
            .background(YChatTheme.colors.background)
            .padding(Dimens.MD)
            .fillMaxSize(),
    ) {
        TypographyStyle.MediumBody.Text(text = stringResource(id = R.string.images_input_title))
        StandardTextField(
            value = viewModel.inputMessage.value,
            hint = stringResource(id = R.string.images_input_hint),
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = Dimens.MD)
            ,
            onTextChanged = { viewModel.onInputMessage(it) },
            enabled = state !is State.Loading,
        )
        ButtonContained(
            text = stringResource(id = R.string.images_action),
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = Dimens.XM),
            enabled = viewModel.onEnableButton.value,
            onClick = { viewModel.requestEdits() }
        )
        Box(modifier = Modifier.fillMaxSize()) {
            when (state) {
                is State.Loading ->
                    CircularProgressIndicator(
                        modifier = Modifier.align(Alignment.Center)
                    )
                is State.Error ->
                    Feedback(
                        modifier = Modifier.align(Alignment.Center),
                        feedbackState = FeedbackState.ERROR
                    )
                is State.Success ->
                    SubcomposeAsyncImage(
                        model = state.generatedImage,
                        loading = { ImagePlaceHolder() },
                        contentDescription = null,
                        contentScale = ContentScale.FillBounds,
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(top = Dimens.XM)
                            .clip(RoundedCornerShape(Dimens.XS))
                    )
                else -> Spacer(modifier = Modifier)
            }
        }
    }
}

@Preview
@Composable
private fun ImagesScreenPreview() {
    YChatTheme {
        val openAi = OpenAI.create(BuildConfig.API_KEY)
        val viewModel = ImagesViewModel(openAi)
        ImagesScreen(viewModel)
    }
}
