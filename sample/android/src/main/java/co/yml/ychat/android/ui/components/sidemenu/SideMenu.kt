package co.yml.ychat.android.ui.components.sidemenu

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import co.yml.ychat.android.ui.components.divider.HorizontalDivider
import co.yml.ychat.android.ui.components.logo.LogoLabel
import co.yml.ychat.android.ui.components.sidemenu.model.MenuItem
import co.yml.ychat.android.ui.theme.Dimens
import co.yml.ychat.android.ui.theme.Icons
import co.yml.ychat.android.ui.theme.TypographyStyle
import co.yml.ychat.android.ui.theme.YChatTheme

@Composable
fun SideMenu(
    items: List<MenuItem>,
    modifier: Modifier = Modifier,
    selectedItem: MenuItem? = null,
    onMenuClicked: (MenuItem) -> Unit = {},
) {
    LazyColumn(
        modifier
            .background(YChatTheme.colors.background)
            .fillMaxHeight()
    ) {
        item { TopHeader() }
        item { HorizontalDivider() }
        item { Spacer(modifier = Modifier.height(Dimens.MD)) }
        items(items) { item ->
            if (item.isTopDividerVisible) HorizontalDivider(
                Modifier
                    .padding(start = Dimens.XL)
                    .padding(vertical = Dimens.XS)
            )
            if (selectedItem == item) SelectedItemMenu(item, onMenuClicked)
            else UnselectedItemMenu(item, onMenuClicked)
        }
    }
}

@Composable
private fun TopHeader() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = Dimens.MD)
            .padding(vertical = Dimens.XM)

    ) {
        LogoLabel()
    }
}

@Composable
private fun SelectedItemMenu(
    menuItem: MenuItem,
    onMenuClicked: (MenuItem) -> Unit = {},
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(end = Dimens.MD)
            .clip(shape = RoundedCornerShape(bottomEnd = Dimens.XXXL, topEnd = Dimens.XXXL))
            .clickable { onMenuClicked(menuItem) }
            .background(YChatTheme.colors.accentLight)
            .padding(Dimens.MD)
    ) {
        menuItem.icon.Icon()
        Spacer(modifier = Modifier.width(Dimens.XS))
        TypographyStyle.MediumTitle.Text(text = menuItem.title)
    }
}

@Composable
private fun UnselectedItemMenu(
    menuItem: MenuItem,
    onMenuClicked: (MenuItem) -> Unit = {},
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(end = Dimens.MD)
            .clip(shape = RoundedCornerShape(bottomEnd = Dimens.XXXL, topEnd = Dimens.XXXL))
            .clickable { onMenuClicked(menuItem) }
            .padding(Dimens.MD)

    ) {
        menuItem.icon.Icon()
        Spacer(modifier = Modifier.width(Dimens.XS))
        TypographyStyle.MediumBody.Text(text = menuItem.title)
    }
}

@Preview
@Composable
private fun SideMenuPreview() {
    YChatTheme(false) {
        val items = listOf(
            MenuItem(
                id = "0",
                title = "Models",
                icon = Icons.Model,
            ),
            MenuItem(
                id = "1",
                title = "Completions",
                icon = Icons.Text,
            ),
            MenuItem(
                id = "3",
                title = "Settings",
                icon = Icons.Settings,
                isTopDividerVisible = true,
            )
        )
        var selectedItem by remember { mutableStateOf(items[0]) }
        SideMenu(items = items, selectedItem = selectedItem) { clickedItem ->
            selectedItem = clickedItem
        }
    }
}
