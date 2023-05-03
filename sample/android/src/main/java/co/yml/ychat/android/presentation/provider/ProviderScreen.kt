package co.yml.ychat.android.presentation.provider

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectable
import androidx.compose.material.RadioButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import co.yml.ychat.android.ui.theme.TypographyStyle
import co.yml.ychat.android.ui.theme.YChatTheme
import org.koin.androidx.compose.getViewModel

@Composable
internal fun ProviderScreen(viewModel: ProviderScreenViewModel = getViewModel()) {

    val providers = viewModel.providers.value

    Column(
        modifier = Modifier
            .background(YChatTheme.colors.background)
            .fillMaxHeight()
            .padding(4.dp),
        verticalArrangement = Arrangement.Top,
    ) {
        Text("Select Provider:")
        providers.forEach { provider ->
            Row(
                Modifier
                    .fillMaxWidth()
                    .selectable(
                        selected = (provider.isSelected),
                        onClick = {
                            viewModel.selectProvider(provider.providerKey)
                        }
                    ),
                verticalAlignment = Alignment.CenterVertically
            ) {
                RadioButton(
                    selected = (provider.isSelected),
                    onClick = {
                        viewModel.selectProvider(provider.providerKey)
                    }
                )
                TypographyStyle.MediumBody.Text(
                    text = provider.name,
                    modifier = Modifier.padding(start = 0.dp)
                )
            }
        }
    }
}

@Preview
@Composable
private fun SettingsScreenPreview() {
    YChatTheme {
        ProviderScreen()
    }
}
