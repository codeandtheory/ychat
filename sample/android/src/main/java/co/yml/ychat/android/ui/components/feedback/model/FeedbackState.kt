package co.yml.ychat.android.ui.components.feedback.model

import androidx.annotation.StringRes
import co.yml.ychat.android.R
import co.yml.ychat.android.ui.theme.Icons

enum class FeedbackState(
    val icon: Icons,
    @StringRes val title: Int,
    @StringRes val message: Int,
    @StringRes val buttonText: Int? = null,
) {
    ERROR(
        Icons.WarningOutline,
        R.string.feedback_state_error_title,
        R.string.feedback_state_error_message,
        R.string.feedback_state_error_action,
    ),
    CONSTRUCTION(
        Icons.Construction,
        R.string.feedback_state_construction_title,
        R.string.feedback_state_construction_message,
    )
}
