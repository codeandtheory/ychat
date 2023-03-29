package co.yml.ychat.android.ui.components.feedback

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import co.yml.ychat.android.ui.components.button.ButtonOutlined
import co.yml.ychat.android.ui.components.feedback.model.FeedbackState
import co.yml.ychat.android.ui.theme.Dimens
import co.yml.ychat.android.ui.theme.Icons
import co.yml.ychat.android.ui.theme.TypographyStyle
import co.yml.ychat.android.ui.theme.YChatTheme

private const val ICON_SIZE = 128

@Composable
fun Feedback(
    feedbackState: FeedbackState,
    onButtonClick: (() -> Unit)? = null,
) {
    Feedback(
        icons = feedbackState.icon,
        title = stringResource(id = feedbackState.title),
        message = stringResource(id = feedbackState.message),
        buttonText = feedbackState.buttonText?.let { stringResource(id = it) },
        onButtonClick = onButtonClick,
    )
}

@Composable
fun Feedback(
    icons: Icons,
    title: String,
    message: String,
    buttonText: String? = null,
    onButtonClick: (() -> Unit)? = null,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(horizontal = Dimens.XXXL)
    ) {
        icons.Icon(
            modifier = Modifier
                .width(ICON_SIZE.dp)
                .height(ICON_SIZE.dp)
                .padding(bottom = Dimens.MD)
        )
        TypographyStyle.LargeTitle.Text(
            text = title,
            modifier = Modifier.padding(bottom = Dimens.XS),
            textAlign = TextAlign.Center,
        )
        TypographyStyle.SmallBody.Text(
            text = message,
            modifier = Modifier.padding(bottom = Dimens.MD),
            textAlign = TextAlign.Center,
        )
        if (onButtonClick != null && buttonText != null) {
            ButtonOutlined(text = buttonText, onClick = onButtonClick)
        }
    }
}

@Preview
@Composable
private fun StandardTextFieldPreview() {
    YChatTheme {
        Column(modifier = Modifier.background(YChatTheme.colors.background)) {
            Feedback(
                feedbackState = FeedbackState.ERROR,
                onButtonClick = {},
            )
            Feedback(
                feedbackState = FeedbackState.CONSTRUCTION,
            )
        }
    }
}
