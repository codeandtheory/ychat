package co.yml.ychat.android.ui.theme

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview

@Stable
inline val Color.Companion.Black1: Color
    get() = Color(color = 0xDE000000)

@Stable
inline val Color.Companion.Black2: Color
    get() = Color(color = 0x8F000000)

@Stable
inline val Color.Companion.Black3: Color
    get() = Color(color = 0x4D000000)

@Stable
inline val Color.Companion.Black4: Color
    get() = Color(color = 0x1F000000)

@Stable
inline val Color.Companion.Black5: Color
    get() = Color(color = 0x0F000000)

@Stable
inline val Color.Companion.White1: Color
    get() = Color(color = 0xFFFFFFFF)

@Stable
inline val Color.Companion.White2: Color
    get() = Color(color = 0xB3FFFFFF)

@Stable
inline val Color.Companion.White3: Color
    get() = Color(color = 0x66FFFFFF)

@Stable
inline val Color.Companion.White4: Color
    get() = Color(color = 0x1FFFFFFF)

@Stable
inline val Color.Companion.White5: Color
    get() = Color(color = 0x0FFFFFFF)

@Stable
inline val Color.Companion.Red1: Color
    get() = Color(color = 0xFFE63746)

@Stable
inline val Color.Companion.Green1: Color
    get() = Color(color = 0xFF19C37D)

@Stable
inline val Color.Companion.Green2: Color
    get() = Color(color = 0xFFD2F4D3)

@Stable
class Colors(
    text1: Color,
    text2: Color,
    text3: Color,
    text4: Color,
    text5: Color,
    background: Color,
    icon1: Color,
    divider: Color,
    accent: Color,
    onAccent: Color,
    accentLight: Color,
    primary1: Color,
    primary2: Color,
    primary3: Color,
    primary4: Color,
    primary5: Color,
) {
    var text1 by mutableStateOf(text1)
        private set
    var text2 by mutableStateOf(text2)
        private set
    var text3 by mutableStateOf(text3)
        private set
    var text4 by mutableStateOf(text4)
        private set
    var text5 by mutableStateOf(text5)
        private set
    var background by mutableStateOf(background)
        private set
    var icon1 by mutableStateOf(icon1)
        private set
    var divider by mutableStateOf(divider)
        private set
    var accent by mutableStateOf(accent)
        private set
    var onAccent by mutableStateOf(onAccent)
        private set
    var accentLight by mutableStateOf(accentLight)
        private set
    var primary1 by mutableStateOf(primary1)
        private set
    var primary2 by mutableStateOf(primary2)
        private set
    var primary3 by mutableStateOf(primary3)
        private set
    var primary4 by mutableStateOf(primary4)
        private set
    var primary5 by mutableStateOf(primary5)
        private set

    fun update(other: Colors) {
        text1 = other.text1
        text2 = other.text2
        text3 = other.text3
        text4 = other.text4
        text5 = other.text5
        background = other.background
        icon1 = other.icon1
        divider = other.divider
        accent = other.accent
        onAccent = other.onAccent
        accentLight = other.accentLight
        primary1 = other.primary1
        primary2 = other.primary2
        primary3 = other.primary3
        primary4 = other.primary4
        primary5 = other.primary5
    }
}

val LightColors by lazy {
    Colors(
        text1 = Color.Black1,
        text2 = Color.Black2,
        text3 = Color.Black3,
        text4 = Color.Black4,
        text5 = Color.Black5,
        background = Color.White1,
        icon1 = Color.Black1,
        divider = Color.Black4,
        accent = Color.Black1,
        onAccent = Color.White1,
        accentLight = Color.Black5,
        primary1 = Color.Black1,
        primary2 = Color.Black2,
        primary3 = Color.Black3,
        primary4 = Color.Black4,
        primary5 = Color.Black5,
    )
}

val DarkColors by lazy {
    Colors(
        text1 = Color.White1,
        text2 = Color.White2,
        text3 = Color.White3,
        text4 = Color.White4,
        text5 = Color.White5,
        background = Color.Black1,
        icon1 = Color.White1,
        divider = Color.White4,
        accent = Color.White1,
        onAccent = Color.Black1,
        accentLight = Color.White5,
        primary1 = Color.White1,
        primary2 = Color.White2,
        primary3 = Color.White3,
        primary4 = Color.White4,
        primary5 = Color.White5,
    )
}

internal val LocalColors = staticCompositionLocalOf<Colors> { error("No Colors provided") }

@Composable
fun ProvideColors(
    colors: Colors,
    content: @Composable () -> Unit
) {
    val colorPalette = remember { colors }
    colorPalette.update(colors)
    CompositionLocalProvider(LocalColors provides colorPalette, content = content)
}

@Preview
@Composable
private fun ColorPreview() {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = Color::Black1.name,
            modifier = Modifier.buildModifier(Color.Black1),
            color = Color.White
        )
        Text(
            text = Color::Black2.name,
            modifier = Modifier.buildModifier(Color.Black2),
            color = Color.White
        )
        Text(
            text = Color::Black3.name,
            modifier = Modifier.buildModifier(Color.Black3),
            color = Color.White
        )
        Text(
            text = Color::Black3.name,
            modifier = Modifier.buildModifier(Color.Black3),
            color = Color.White
        )
        Text(
            text = Color::Black4.name,
            modifier = Modifier.buildModifier(Color.Black4),
            color = Color.White
        )
        Text(
            text = Color::Black5.name,
            modifier = Modifier.buildModifier(Color.Black5),
            color = Color.White
        )
        Text(
            text = Color::White1.name,
            modifier = Modifier.buildModifier(Color.White1),
            color = Color.Black
        )
        Text(
            text = Color::White2.name,
            modifier = Modifier.buildModifier(Color.White2),
            color = Color.Black
        )
        Text(
            text = Color::White3.name,
            modifier = Modifier.buildModifier(Color.White3),
            color = Color.Black
        )
        Text(
            text = Color::White4.name,
            modifier = Modifier.buildModifier(Color.White4),
            color = Color.White
        )
        Text(
            text = Color::White5.name,
            modifier = Modifier.buildModifier(Color.White5),
            color = Color.White
        )
        Text(
            text = Color::Red1.name,
            modifier = Modifier.buildModifier(Color.Red1),
            color = Color.White
        )
        Text(
            text = Color::Green2.name,
            modifier = Modifier.buildModifier(Color.Green2),
            color = Color.Black
        )
    }
}

@Preview
@Composable
private fun LightColorsPreview() {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = LightColors::text1.name,
            modifier = Modifier.buildModifier(LightColors.text1),
            color = Color.White
        )
        Text(
            text = LightColors::text2.name,
            modifier = Modifier.buildModifier(LightColors.text2),
            color = Color.White
        )
        Text(
            text = LightColors::text3.name,
            modifier = Modifier.buildModifier(LightColors.text3),
            color = Color.White
        )
        Text(
            text = LightColors::text4.name,
            modifier = Modifier.buildModifier(LightColors.text4),
            color = Color.White
        )
        Text(
            text = LightColors::text5.name,
            modifier = Modifier.buildModifier(LightColors.text5),
            color = Color.White
        )
        Text(
            text = LightColors::background.name,
            modifier = Modifier.buildModifier(LightColors.background),
            color = Color.Black
        )
        Text(
            text = LightColors::icon1.name,
            modifier = Modifier.buildModifier(LightColors.icon1),
            color = Color.White
        )
        Text(
            text = LightColors::divider.name,
            modifier = Modifier.buildModifier(LightColors.divider),
            color = Color.White
        )
        Text(
            text = LightColors::accent.name,
            modifier = Modifier.buildModifier(LightColors.accent),
            color = Color.White
        )
        Text(
            text = LightColors::onAccent.name,
            modifier = Modifier.buildModifier(LightColors.onAccent),
            color = Color.Black
        )
        Text(
            text = LightColors::accentLight.name,
            modifier = Modifier.buildModifier(LightColors.accentLight),
            color = Color.White
        )
        Text(
            text = LightColors::primary1.name,
            modifier = Modifier.buildModifier(LightColors.primary1),
            color = Color.White
        )
        Text(
            text = LightColors::primary2.name,
            modifier = Modifier.buildModifier(LightColors.primary2),
            color = Color.White
        )
        Text(
            text = LightColors::primary3.name,
            modifier = Modifier.buildModifier(LightColors.primary3),
            color = Color.White
        )
        Text(
            text = LightColors::primary4.name,
            modifier = Modifier.buildModifier(LightColors.primary4),
            color = Color.White
        )
        Text(
            text = LightColors::primary5.name,
            modifier = Modifier.buildModifier(LightColors.primary5),
            color = Color.White
        )
    }
}

@Preview
@Composable
private fun DarkColorsPreview() {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = DarkColors::text1.name,
            modifier = Modifier.buildModifier(DarkColors.text1),
            color = Color.Black
        )
        Text(
            text = DarkColors::text2.name,
            modifier = Modifier.buildModifier(DarkColors.text2),
            color = Color.Black
        )
        Text(
            text = DarkColors::text3.name,
            modifier = Modifier.buildModifier(DarkColors.text3),
            color = Color.White
        )
        Text(
            text = DarkColors::text4.name,
            modifier = Modifier.buildModifier(DarkColors.text4),
            color = Color.White
        )
        Text(
            text = DarkColors::text5.name,
            modifier = Modifier.buildModifier(DarkColors.text5),
            color = Color.White
        )
        Text(
            text = DarkColors::background.name,
            modifier = Modifier.buildModifier(DarkColors.background),
            color = Color.White
        )
        Text(
            text = DarkColors::icon1.name,
            modifier = Modifier.buildModifier(DarkColors.icon1),
            color = Color.Black
        )
        Text(
            text = DarkColors::divider.name,
            modifier = Modifier.buildModifier(DarkColors.divider),
            color = Color.White
        )
        Text(
            text = DarkColors::accent.name,
            modifier = Modifier.buildModifier(DarkColors.accent),
            color = Color.Black
        )
        Text(
            text = DarkColors::onAccent.name,
            modifier = Modifier.buildModifier(DarkColors.onAccent),
            color = Color.White
        )
        Text(
            text = DarkColors::accentLight.name,
            modifier = Modifier.buildModifier(DarkColors.accentLight),
            color = Color.White
        )
        Text(
            text = DarkColors::primary1.name,
            modifier = Modifier.buildModifier(DarkColors.primary1),
            color = Color.Black
        )
        Text(
            text = DarkColors::primary2.name,
            modifier = Modifier.buildModifier(DarkColors.primary2),
            color = Color.Black
        )
        Text(
            text = DarkColors::primary3.name,
            modifier = Modifier.buildModifier(DarkColors.primary3),
            color = Color.Black
        )
        Text(
            text = DarkColors::primary4.name,
            modifier = Modifier.buildModifier(DarkColors.primary4),
            color = Color.White
        )
        Text(
            text = DarkColors::primary5.name,
            modifier = Modifier.buildModifier(DarkColors.primary5),
            color = Color.White
        )
    }
}

private fun Modifier.buildModifier(color: Color): Modifier {
    return this
        .background(color)
        .fillMaxWidth()
        .padding(Dimens.MD)
}
