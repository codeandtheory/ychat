package co.yml.ychat.android.ui.components.textfield

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import co.yml.ychat.android.ui.theme.Dimens
import co.yml.ychat.android.ui.theme.TypographyStyle
import co.yml.ychat.android.ui.theme.YChatTheme

private const val ENABLED_OPACITY = 1F
private const val DISABLED_OPACITY = 0.4F

@Composable
internal fun StandardTextField(
    value: String,
    modifier: Modifier = Modifier,
    hint: String = "",
    enabled: Boolean = true,
    readOnly: Boolean = false,
    maxLines: Int = 1,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    onTextChanged: (String) -> Unit = {},
) {
    TextField(
        value = value,
        onValueChange = { onTextChanged(it) },
        modifier = modifier.alpha(if (enabled) ENABLED_OPACITY else DISABLED_OPACITY),
        enabled = enabled,
        readOnly = readOnly,
        keyboardOptions = keyboardOptions,
        maxLines = maxLines,
        shape = RoundedCornerShape(Dimens.XS),
        placeholder = {
            TypographyStyle.MediumBody.Text(
                text = hint,
                color = YChatTheme.colors.text3
            )
        },
        colors = TextFieldDefaults.textFieldColors(
            textColor = YChatTheme.colors.text1,
            backgroundColor = YChatTheme.colors.primary5,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent
        )
    )
}

@Preview
@Composable
private fun StandardTextFieldPreview() {
    YChatTheme {
        Column(
            Modifier
                .background(YChatTheme.colors.background)
                .padding(Dimens.MD)
        ) {
            StandardTextField(value = "", hint = "Preview")
        }
    }
}
