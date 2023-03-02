package co.yml.ychat.android

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Shapes
import androidx.compose.material.Typography
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import co.yml.ychat.android.ui.Dimensions.default
import co.yml.ychat.android.ui.Dimensions.spaceExtraSmall

@Composable
fun MyApplicationTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) {
        darkColors(
            primary = colorResource(id = R.color.softPurple),
            primaryVariant = colorResource(id = R.color.deepBlue),
            secondary = colorResource(id = R.color.softGreen),
            background = colorResource(id = R.color.darkGrey),
        )
    } else {
        lightColors(
            primary = colorResource(id = R.color.softBlue),
            primaryVariant = colorResource(id = R.color.deepBlue),
            secondary = colorResource(id = R.color.softGreen),
            background = colorResource(id = R.color.white),
        )
    }
    val typography = Typography(
        body1 = TextStyle(
            fontFamily = FontFamily.Default,
            fontWeight = FontWeight.Normal,
            fontSize = dimensionResource(id = R.dimen.font14).value.sp
        )
    )
    val shapes = Shapes(
        small = RoundedCornerShape(spaceExtraSmall),
        medium = RoundedCornerShape(spaceExtraSmall),
        large = RoundedCornerShape(default)
    )

    MaterialTheme(
        colors = colors,
        typography = typography,
        shapes = shapes,
        content = content
    )
}
