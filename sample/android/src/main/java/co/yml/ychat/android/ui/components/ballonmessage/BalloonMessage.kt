package co.yml.ychat.android.ui.components.ballonmessage

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import co.yml.ychat.android.ui.components.loading.TypingTextLoading
import co.yml.ychat.android.ui.theme.Dimens
import co.yml.ychat.android.ui.theme.Green1
import co.yml.ychat.android.ui.theme.Icons
import co.yml.ychat.android.ui.theme.Red1
import co.yml.ychat.android.ui.theme.TypographyStyle
import co.yml.ychat.android.ui.theme.White1
import co.yml.ychat.android.ui.theme.YChatTheme

@Composable
fun BalloonSenderMessage(
    text: String,
    isError: Boolean = false,
) {
    Box(
        Modifier
            .fillMaxWidth()
            .padding(start = Dimens.XXXL),
        contentAlignment = Alignment.CenterEnd
    ) {
        Row(
            modifier = Modifier
                .clip(
                    RoundedCornerShape(
                        topStart = Dimens.MD,
                        topEnd = Dimens.MD,
                        bottomEnd = Dimens.MD,
                    )
                )
                .background(YChatTheme.colors.accent)
                .padding(horizontal = Dimens.MD)
                .padding(vertical = Dimens.XS)
        ) {
            TypographyStyle.MediumBody.Text(
                text = text,
                color = YChatTheme.colors.onAccent
            )
            if (isError) {
                Icons.Warning.Icon(
                    tint = Color.Red1,
                    modifier = Modifier.padding(start = Dimens.XS)
                )
            }
        }
    }
}

@Composable
fun BalloonBotMessage(text: String) {
    Box(
        Modifier
            .fillMaxWidth()
            .padding(end = Dimens.XXXL),
        contentAlignment = Alignment.CenterStart
    ) {
        Row(horizontalArrangement = Arrangement.spacedBy(Dimens.XXS)) {
            Icons.Bot.Icon(
                Modifier
                    .clip(CircleShape)
                    .background(Color.Green1)
                    .padding(Dimens.XS),
                tint = Color.White1
            )
            TypographyStyle.MediumBody.Text(
                text = text,
                modifier = Modifier
                    .clip(
                        RoundedCornerShape(
                            bottomStart = Dimens.MD,
                            topEnd = Dimens.MD,
                            bottomEnd = Dimens.MD,
                        )
                    )
                    .background(YChatTheme.colors.primary4)
                    .padding(horizontal = Dimens.MD)
                    .padding(vertical = Dimens.XS),
                color = YChatTheme.colors.text1
            )
        }
    }
}

@Composable
fun BalloonTyping() {
    Box(
        Modifier.fillMaxWidth(),
        contentAlignment = Alignment.CenterStart
    ) {
        TypingTextLoading(
            modifier = Modifier
                .clip(
                    RoundedCornerShape(
                        bottomStart = Dimens.MD,
                        topEnd = Dimens.MD,
                        bottomEnd = Dimens.MD,
                    )
                )
                .background(YChatTheme.colors.primary4)
                .padding(horizontal = Dimens.MD)
                .padding(vertical = Dimens.XS),
        )
    }
}

@Preview
@Composable
private fun BalloonPreview() {
    YChatTheme {
        Column(
            Modifier
                .background(YChatTheme.colors.background)
                .padding(Dimens.SM),
            verticalArrangement = Arrangement.spacedBy(Dimens.SM)
        ) {
            BalloonSenderMessage("Say this is a test")
            BalloonSenderMessage("Say this is a test", true)
            BalloonBotMessage("This is indeed a test")
            BalloonTyping()
        }
    }
}