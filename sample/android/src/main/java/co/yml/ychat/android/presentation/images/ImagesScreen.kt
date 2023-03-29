package co.yml.ychat.android.presentation.images

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import co.yml.ychat.android.ui.components.feedback.Feedback
import co.yml.ychat.android.ui.components.feedback.model.FeedbackState
import co.yml.ychat.android.ui.theme.YChatTheme

@Composable
internal fun ImagesScreen() {
    Column(
        modifier = Modifier
            .background(YChatTheme.colors.background)
            .fillMaxHeight(),
        verticalArrangement = Arrangement.Center,
    ) {
        Feedback(feedbackState = FeedbackState.CONSTRUCTION)
    }
}

@Preview
@Composable
private fun ImagesScreenPreview() {
    YChatTheme {
        ImagesScreen()
    }
}
