package co.yml.ychat.android.ui.components.button

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import co.yml.ychat.android.ui.theme.Dimens
import co.yml.ychat.android.ui.theme.YChatTheme

@Composable
internal fun ButtonContained(
    text: String,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    onClick: () -> Unit = {},
) {
    Button(
        modifier = modifier,
        enabled = enabled,
        shape = RoundedCornerShape(Dimens.XS),
        contentPadding = PaddingValues(Dimens.MD),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = YChatTheme.colors.accent,
            contentColor = YChatTheme.colors.onAccent,
            disabledBackgroundColor = YChatTheme.colors.primary5,
            disabledContentColor = YChatTheme.colors.primary4
        ),
        onClick = onClick
    ) { Text(text = text) }
}

@Preview
@Composable
private fun ButtonContainedPreview() {
    YChatTheme(darkTheme = false) {
        ButtonContained(
            text = "Preview",
            enabled = true
        )
    }
}
