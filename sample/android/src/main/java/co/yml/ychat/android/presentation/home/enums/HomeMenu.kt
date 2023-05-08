package co.yml.ychat.android.presentation.home.enums

import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import co.yml.ychat.android.R
import co.yml.ychat.android.data.ProviderKey
import co.yml.ychat.android.ui.components.sidemenu.model.MenuItem
import co.yml.ychat.android.ui.theme.Icons

internal enum class HomeMenu(
    val id: String,
    val icons: Icons,
    @StringRes val title: Int,
    private val isTopDividerVisible: Boolean,
    val availability: List<ProviderKey> = ProviderKey.values().toList(),
) {
    MODELS(
        id = "models",
        icons = Icons.Model,
        title = R.string.home_models_title,
        isTopDividerVisible = false,
        availability = listOf(ProviderKey.OPENAI),
    ),
    COMPLETIONS(
        id = "completions",
        icons = Icons.Text,
        title = R.string.home_completions_title,
        isTopDividerVisible = false,
    ),
    CHAT_COMPLETIONS(
        id = "chat_completions",
        icons = Icons.ChatBubbleOutline,
        title = R.string.home_chat_completions_title,
        isTopDividerVisible = false,
        availability = listOf(ProviderKey.OPENAI),
    ),
    EDITS(
        id = "edits",
        icons = Icons.Edit,
        title = R.string.home_chat_edits_title,
        isTopDividerVisible = false,
        availability = listOf(ProviderKey.OPENAI),
    ),
    IMAGES(
        id = "images",
        icons = Icons.Image,
        title = R.string.home_chat_images_title,
        isTopDividerVisible = false,
        availability = listOf(ProviderKey.OPENAI),
    ),
    AUDIO(
        id = "audio",
        icons = Icons.Audio,
        title = R.string.home_chat_audio_title,
        isTopDividerVisible = false,
        availability = listOf(ProviderKey.OPENAI),
    ),
    PROVIDER(
        id = "provider",
        icons = Icons.Audio,
        title = R.string.home_chat_audio_title,
        isTopDividerVisible = false,
        availability = listOf(ProviderKey.OPENAI),
    ),
    SETTINGS(
        id = "settings",
        icons = Icons.Settings,
        title = R.string.home_chat_settings_title,
        isTopDividerVisible = true,
    );

    val titleString: String
        @Composable get() = stringResource(id = title)

    @Composable
    fun getMenuItem(): MenuItem {
        return MenuItem(id, stringResource(id = title), icons, isTopDividerVisible)
    }

    companion object {

        fun findById(id: String): HomeMenu {
            return values().first { it.id == id }
        }

        @Composable
        fun getMenuItems(): List<MenuItem> {
            return values().map { it.getMenuItem() }
        }
    }
}
