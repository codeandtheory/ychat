package co.yml.ychat.android.presentation.chatcompletions.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import co.yml.ychat.YChat
import co.yml.ychat.android.BuildConfig
import co.yml.ychat.android.R
import co.yml.ychat.android.presentation.chatcompletions.model.MessageType
import co.yml.ychat.android.presentation.chatcompletions.viewmodel.ChatCompletionsViewModel
import co.yml.ychat.android.ui.components.ballonmessage.BalloonBotMessage
import co.yml.ychat.android.ui.components.ballonmessage.BalloonSenderMessage
import co.yml.ychat.android.ui.components.ballonmessage.BalloonTyping
import co.yml.ychat.android.ui.components.textfield.StandardTextField
import co.yml.ychat.android.ui.theme.Dimens
import co.yml.ychat.android.ui.theme.Icons
import co.yml.ychat.android.ui.theme.TypographyStyle
import co.yml.ychat.android.ui.theme.YChatTheme
import org.koin.androidx.compose.getViewModel

@Composable
internal fun ChatCompletionsScreen(
    viewModel: ChatCompletionsViewModel = getViewModel(),
) {
    Column(
        Modifier
            .background(YChatTheme.colors.background)
            .fillMaxHeight()
    ) {
        val scrollState = rememberLazyListState()
        val messages = viewModel.messages
        LazyColumn(
            state = scrollState,
            verticalArrangement = Arrangement.spacedBy(Dimens.MD),
            modifier = Modifier
                .weight(1F)
                .padding(horizontal = Dimens.XS),
        ) {
            item { Spacer(modifier = Modifier.padding(top = Dimens.MD)) }
            if (messages.isEmpty()) item { EmptyMessage() }
            items(messages) { item ->
                when (item) {
                    is MessageType.Sender ->
                        BalloonSenderMessage(text = item.text, isError = item.isError)
                    is MessageType.Bot ->
                        BalloonBotMessage(text = item.text)
                    is MessageType.Loading ->
                        BalloonTyping()
                }
            }
        }
        SendMessageSection(viewModel)
        LaunchedEffect(messages.size) {
            scrollState.animateScrollToItem(messages.size)
        }
    }
}

@Composable
private fun EmptyMessage() {
    TypographyStyle.SmallTitle.Text(
        text = stringResource(id = R.string.chat_completions_empty_message),
        modifier = Modifier
            .padding(horizontal = Dimens.XM)
            .clip(RoundedCornerShape(Dimens.MD))
            .background(YChatTheme.colors.accentLight)
            .padding(vertical = Dimens.SM)
            .fillMaxWidth(),
        textAlign = TextAlign.Center,
    )
}

@Composable
private fun SendMessageSection(viewModel: ChatCompletionsViewModel) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(Dimens.XS),
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(Dimens.SM)
    ) {
        StandardTextField(
            viewModel.message.value,
            hint = stringResource(id = R.string.chat_completions_hint),
            enabled = viewModel.onEnableTextField.value,
            keyboardOptions = KeyboardOptions(capitalization = KeyboardCapitalization.Sentences),
            onTextChanged = { viewModel.onMessage(it) }
        )
        SendButton(viewModel)
    }
}

@Composable
private fun SendButton(viewModel: ChatCompletionsViewModel) {
    val background =
        if (viewModel.onEnableButton.value) YChatTheme.colors.accent
        else YChatTheme.colors.primary4
    Icons.Send.IconButton(
        onClick = { viewModel.sendMessage() },
        modifier = Modifier
            .padding(Dimens.XS)
            .clip(CircleShape)
            .background(background),
        tint = YChatTheme.colors.onAccent,
        enabled = viewModel.onEnableButton.value
    )
}

@Preview
@Composable
private fun StandardTextFieldPreview() {
    YChatTheme {
        val yChat = YChat.create(BuildConfig.API_KEY)
        val viewModel = ChatCompletionsViewModel(yChat)
        ChatCompletionsScreen(viewModel)
    }
}
