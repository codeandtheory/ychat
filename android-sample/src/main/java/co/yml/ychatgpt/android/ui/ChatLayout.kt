package co.yml.ychatgpt.android.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import co.yml.ychatgpt.android.MessageItem

@Composable
fun ChatLayout(
    messages: List<MessageItem>
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
        ,
    ) {
        items(messages) { message ->
            MessageItemLayout(
                messageText = message.message, isOut = message.isOut
            )
        }
    }
}