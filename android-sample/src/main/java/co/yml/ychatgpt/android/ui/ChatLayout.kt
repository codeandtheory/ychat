package co.yml.ychatgpt.android.ui

import android.content.res.Configuration
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import co.yml.ychatgpt.android.MessageItem
import co.yml.ychatgpt.android.ui.Dimensions.spaceMedium

@Composable
fun ChatLayout(
    messages: List<MessageItem>
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(spaceMedium),
    ) {
        items(messages) { message ->
            MessageItemLayout(
                messageText = message.message, isOut = message.isOut
            )
        }
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun PreviewChatLayout() {
    ChatLayout(
        messages = listOf(
            MessageItem(message = "message 1", isOut = true),
            MessageItem(message = "message 2", isOut = false)
        )
    )
}