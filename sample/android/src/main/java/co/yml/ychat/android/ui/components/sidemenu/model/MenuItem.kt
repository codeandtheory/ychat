package co.yml.ychat.android.ui.components.sidemenu.model

import co.yml.ychat.android.ui.theme.Icons

data class MenuItem(
    val id: String,
    val title: String,
    val icon: Icons,
    val isTopDividerVisible: Boolean = false,
)
