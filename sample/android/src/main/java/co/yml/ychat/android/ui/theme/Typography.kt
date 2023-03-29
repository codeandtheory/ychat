package co.yml.ychat.android.ui.theme

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp

sealed class TypographyStyle(private val textStyle: TextStyle) {

    object DisplayTitle : TypographyStyle(
        TextStyle(
            fontWeight = FontWeight.Medium,
            fontSize = 19.sp
        )
    )

    object DisplayBody : TypographyStyle(
        TextStyle(
            fontWeight = FontWeight.Light,
            fontSize = 19.sp
        )
    )

    object LargeTitle : TypographyStyle(
        TextStyle(
            fontWeight = FontWeight.Medium,
            fontSize = 17.sp
        )
    )

    object LargeBody : TypographyStyle(
        TextStyle(
            fontWeight = FontWeight.Light,
            fontSize = 17.sp
        )
    )

    object MediumTitle : TypographyStyle(
        TextStyle(
            fontWeight = FontWeight.Bold,
            fontSize = 15.sp
        )
    )

    object MediumBody : TypographyStyle(
        TextStyle(
            fontWeight = FontWeight.Normal,
            fontSize = 15.sp
        )
    )

    object SmallTitle : TypographyStyle(
        TextStyle(
            fontWeight = FontWeight.Bold,
            fontSize = 13.sp
        )
    )

    object SmallBody : TypographyStyle(
        TextStyle(
            fontWeight = FontWeight.Normal,
            fontSize = 13.sp
        )
    )

    object ExtraSmallTitle : TypographyStyle(
        TextStyle(
            fontWeight = FontWeight.Bold,
            fontSize = 11.sp
        )
    )

    object ExtraSmallBody : TypographyStyle(
        TextStyle(
            fontWeight = FontWeight.Normal,
            fontSize = 11.sp,
            fontStyle = FontStyle.Italic,
        )
    )

    @Composable
    fun Text(
        text: String,
        modifier: Modifier = Modifier,
        color: Color = YChatTheme.colors.text1,
        textAlign: TextAlign? = null,
    ) {
        Text(
            text = text,
            color = color,
            style = textStyle,
            modifier = modifier,
            textAlign = textAlign
        )
    }

}

@Preview
@Composable
private fun TypographyPreview() {
    YChatTheme {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(YChatTheme.colors.background),
        ) {
            TextPreview(TypographyStyle.DisplayTitle)
            TextPreview(TypographyStyle.DisplayBody)
            TextPreview(TypographyStyle.LargeTitle)
            TextPreview(TypographyStyle.LargeBody)
            TextPreview(TypographyStyle.MediumTitle)
            TextPreview(TypographyStyle.MediumBody)
            TextPreview(TypographyStyle.SmallTitle)
            TextPreview(TypographyStyle.SmallBody)
            TextPreview(TypographyStyle.ExtraSmallTitle)
            TextPreview(TypographyStyle.ExtraSmallBody)
        }
    }
}

@Composable
private fun TextPreview(typographyStyle: TypographyStyle) {
    typographyStyle.Text(text = typographyStyle::class.simpleName.orEmpty())
}
