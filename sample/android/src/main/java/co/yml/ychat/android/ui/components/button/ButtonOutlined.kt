package co.yml.ychat.android.ui.components.button

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.OutlinedButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import co.yml.ychat.android.ui.theme.Dimens
import co.yml.ychat.android.ui.theme.TypographyStyle
import co.yml.ychat.android.ui.theme.YChatTheme

@Composable
fun ButtonOutlined(
    text: String,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    onClick: () -> Unit = {},
) {
    val borderColor = if (enabled) YChatTheme.colors.accent else YChatTheme.colors.primary4
    val textColor = if (enabled) YChatTheme.colors.accent else YChatTheme.colors.primary4
    OutlinedButton(
        onClick = onClick,
        modifier = modifier.fillMaxWidth(),
        enabled = enabled,
        shape = RoundedCornerShape(Dimens.XS),
        border = BorderStroke(1.dp, borderColor),
        colors = ButtonDefaults.outlinedButtonColors(
            contentColor =  borderColor,
            backgroundColor = Color.Transparent,
        ),
        contentPadding = PaddingValues(Dimens.SM),
    ) {
        TypographyStyle.SmallTitle.Text(text = text, color = textColor)
    }
}

@Preview
@Composable
private fun ButtonOutlinedPreview() {
    YChatTheme {
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth()
                .background(YChatTheme.colors.background),
            verticalArrangement = Arrangement.Center
        ) {
            ButtonOutlined("Outlined button")
            ButtonOutlined("Outlined button", enabled = false)
        }
    }
}