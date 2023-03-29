package co.yml.ychat.android.ui.components.loading

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import co.yml.ychat.android.R
import co.yml.ychat.android.ui.theme.Dimens
import co.yml.ychat.android.ui.theme.TypographyStyle
import co.yml.ychat.android.ui.theme.YChatTheme
import kotlinx.coroutines.delay

private const val TYPING_INTERVAL = 250L

@Composable
fun TypingTextLoading(modifier: Modifier = Modifier) {
    val typingProgressOne = stringResource(id = R.string.typing_loading_one)
    val typingProgressTwo = stringResource(id = R.string.typing_loading_two)
    val typingProgressThree = stringResource(id = R.string.typing_loading_three)
    var textToDisplay by remember {
        mutableStateOf(typingProgressOne)
    }
    TypographyStyle.MediumBody.Text(
        modifier = modifier,
        text = textToDisplay,
        color = YChatTheme.colors.text3
    )
    LaunchedEffect(Unit) {
        while (true) {
            textToDisplay = when (textToDisplay) {
                typingProgressThree -> typingProgressOne
                typingProgressOne -> typingProgressTwo
                else -> typingProgressThree
            }
            delay(TYPING_INTERVAL)
        }
    }
}

@Preview
@Composable
private fun TypingTextLoadingPreview() {
    YChatTheme {
        Column(
            Modifier
                .background(YChatTheme.colors.background)
                .padding(Dimens.SM),
            verticalArrangement = Arrangement.spacedBy(Dimens.SM)
        ) {
            TypingTextLoading()
        }
    }
}
