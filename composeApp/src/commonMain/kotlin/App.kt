import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.koinScreenModel
import cafe.adriel.voyager.navigator.Navigator
import com.multiplatform.webview.web.WebView
import com.multiplatform.webview.web.rememberWebViewState
import core.network.provideHttpClient
import core.network.provideViewModel
import features.auth.presentation.ui.AuthScreen
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

import gps_tracker.composeapp.generated.resources.Res
import gps_tracker.composeapp.generated.resources.compose_multiplatform
import io.ktor.client.plugins.logging.DEFAULT
import io.ktor.client.plugins.logging.Logger
import org.koin.compose.KoinApplication

@Composable
@Preview
fun App() {
    KoinApplication(
        application = {
            modules(provideHttpClient, provideViewModel)
        }
    ) {
        Navigator(AuthScreen())
    }
}

class MainScreen: Screen {
    @Composable
    override fun Content() {
        val viewModel = koinScreenModel<TestViewModel>()
        val state by viewModel.state.collectAsState()
        LaunchedEffect(true) {
            viewModel.getData()
        }
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                state
            )
        }
    }

}