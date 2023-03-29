package co.yml.ychat.android.ui.components.itemmenu

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import co.yml.ychat.android.ui.components.divider.HorizontalDivider
import co.yml.ychat.android.ui.theme.Dimens
import co.yml.ychat.android.ui.theme.TypographyStyle
import co.yml.ychat.android.ui.theme.YChatTheme

@Composable
fun ItemMenu(
    startText: String,
    caption: String,
    isDividerVisible: Boolean = true,
) {
    Column {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = Dimens.MD, vertical = 14.dp),
            verticalArrangement = Arrangement.spacedBy(Dimens.XXS)
        ) {
            TypographyStyle.MediumBody.Text(text = startText)
            TypographyStyle.SmallBody.Text(text = caption, color = YChatTheme.colors.text2)
        }
        if (isDividerVisible) {
            HorizontalDivider(modifier = Modifier.padding(horizontal = Dimens.MD))
        }
    }
}

@Preview
@Composable
private fun ItemMenuPreview() {
    YChatTheme {
        Column(
            modifier = Modifier
                .background(YChatTheme.colors.background)
                .fillMaxHeight()
        ) {
            ItemMenu(startText = "Label one line", caption = "Caption one line")
        }
    }
}
