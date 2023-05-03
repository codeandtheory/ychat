package co.yml.ychat.android.ui.components.itemmenu

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import co.yml.ychat.android.R
import co.yml.ychat.android.ui.components.divider.HorizontalDivider
import co.yml.ychat.android.ui.theme.Dimens
import co.yml.ychat.android.ui.theme.TypographyStyle
import co.yml.ychat.android.ui.theme.YChatTheme

@Composable
fun ItemMenu(
    leftImage: Int? = null,
    startText: String,
    caption: String? = null,
    metaText: String? = null,
    isDividerVisible: Boolean = true,
    rightImage: Int? = null,
    onClick: (() -> Unit)? = null,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .clickable {
                onClick?.invoke()
            }
            .padding(horizontal = Dimens.MD, vertical = 14.dp),
    ) {
        leftImage?.let {
            Image(
                modifier = Modifier
                    .padding(start = 2.dp, end = Dimens.MD)
                    .height(24.dp)
                    .width(24.dp),
                colorFilter = ColorFilter.tint(YChatTheme.colors.accent),
                painter = painterResource(id = it), contentDescription = null
            )
        }
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(Dimens.XXS)
            ) {
                TypographyStyle.MediumBody.Text(text = startText)
                caption?.let {
                    TypographyStyle.SmallBody.Text(
                        text = it,
                        color = YChatTheme.colors.text2
                    )
                }
            }
            if (isDividerVisible) {
                HorizontalDivider(modifier = Modifier.padding(horizontal = Dimens.MD))
            }
        }
        Row {
            metaText?.let {
                TypographyStyle.SmallBody.Text(
                    text = it
                )
            }
            rightImage?.let {
                Image(
                    painter = painterResource(id = it),
                    contentDescription = null,
                    colorFilter = ColorFilter.tint(YChatTheme.colors.accent),
                )
            }
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
        ) {
            ItemMenu(startText = "Label one line", caption = "Caption one line")
        }
    }
}

@Preview
@Composable
private fun ItemMenuWithIconAndMeteTextPreview() {
    YChatTheme {
        Column(
            modifier = Modifier
                .background(YChatTheme.colors.background)
        ) {
            ItemMenu(
                leftImage = R.drawable.ic_api,
                startText = "Label one line",
                metaText = "Info",
                isDividerVisible = false
            )
        }
    }
}
