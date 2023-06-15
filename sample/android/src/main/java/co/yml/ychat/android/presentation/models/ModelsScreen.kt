package co.yml.ychat.android.presentation.models

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import co.yml.openai.provider.OpenAI
import co.yml.openai.provider.domain.model.AIModel
import co.yml.ychat.android.BuildConfig
import co.yml.ychat.android.presentation.models.viewmodel.ModelsViewModel
import co.yml.ychat.android.presentation.models.viewmodel.ModelsViewModel.State
import co.yml.ychat.android.ui.components.feedback.Feedback
import co.yml.ychat.android.ui.components.feedback.model.FeedbackState
import co.yml.ychat.android.ui.components.itemmenu.ItemMenu
import co.yml.ychat.android.ui.theme.Dimens
import co.yml.ychat.android.ui.theme.YChatTheme
import org.koin.androidx.compose.getViewModel

@Composable
internal fun ModelsScreen(viewModel: ModelsViewModel = getViewModel()) {
    val state = viewModel.state.collectAsState().value
    Box(
        modifier = Modifier
            .background(YChatTheme.colors.background)
            .fillMaxHeight()
            .fillMaxWidth()
    ) {
        when (state) {
            is State.Loading ->
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center)
                )
            is State.Error ->
                Feedback(
                    modifier = Modifier.align(Alignment.Center),
                    feedbackState = FeedbackState.ERROR,
                    onButtonClick = { viewModel.fetchModels() }
                )
            is State.Success ->
                ModelListContent(state.models)
        }
    }
}

@Composable
private fun ModelListContent(models: List<AIModel>) {
    LazyColumn {
        item { Spacer(modifier = Modifier.padding(top = Dimens.MD)) }
        items(models) {
            val enableDivider = models.last() != it
            ItemMenu(
                startText = it.id,
                caption = it.ownedBy,
                isDividerVisible = enableDivider,
            )
        }
    }
}

@Preview
@Composable
private fun ModelsScreenPreview() {
    YChatTheme {
        val openAi = OpenAI.create(BuildConfig.API_KEY)
        val viewModel = ModelsViewModel(openAi)
        ModelsScreen(viewModel)
    }
}
