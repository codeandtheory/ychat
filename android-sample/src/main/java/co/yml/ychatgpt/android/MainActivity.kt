package co.yml.ychatgpt.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.lifecycleScope
import co.yml.ychatgpt.ChatGpt
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : ComponentActivity() {

    private val viewModel: MainViewModel by viewModel()

    private val chatGpt by lazy { ChatGpt.create(BuildConfig.API_KEY) }

    private val myCoroutineContext by lazy { lifecycleScope.coroutineContext }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme() {
                Navigation(chatGpt, myCoroutineContext)
            }
        }
    }
}