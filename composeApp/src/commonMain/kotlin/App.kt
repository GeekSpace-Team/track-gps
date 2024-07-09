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
import cafe.adriel.lyricist.ProvideStrings
import cafe.adriel.lyricist.rememberStrings
import cafe.adriel.lyricist.strings
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.getNavigatorScreenModel
import cafe.adriel.voyager.koin.koinNavigatorScreenModel
import cafe.adriel.voyager.koin.koinScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.multiplatform.webview.web.WebView
import com.multiplatform.webview.web.rememberWebViewState
import com.russhwolf.settings.ExperimentalSettingsApi
import com.russhwolf.settings.ObservableSettings
import com.russhwolf.settings.Settings
import com.russhwolf.settings.coroutines.getStringFlow
import com.russhwolf.settings.coroutines.toFlowSettings
import com.russhwolf.settings.coroutines.toSuspendSettings
import core.locale.Locales
import core.network.provideHttpClient
import core.network.provideSettings
import core.network.provideViewModel
import features.auth.presentation.ui.AuthScreen
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

import gps_tracker.composeapp.generated.resources.Res
import gps_tracker.composeapp.generated.resources.compose_multiplatform
import io.ktor.client.plugins.logging.DEFAULT
import io.ktor.client.plugins.logging.Logger
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.launch
import org.koin.compose.KoinApplication
import org.koin.compose.koinInject
import kotlin.random.Random

@Composable
@Preview
fun App() {
    KoinApplication(
        application = {
            modules(provideHttpClient, provideViewModel, provideSettings)
        }
    ) {
        Navigator(MainScreen())
    }
}

class MainScreen: Screen {
    @OptIn(ExperimentalSettingsApi::class)
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow

        val viewModel = navigator.koinNavigatorScreenModel<TestViewModel>()
        val settings = koinInject<Settings>()
        val state by viewModel.state.collectAsState()
        val test = settings.getStringOrNull("test")
        val coroutine = rememberCoroutineScope()
        LaunchedEffect(true) {
            viewModel.getData()
        }
        val lyricist = rememberStrings()
        ProvideStrings(lyricist) {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    state
                )
                Button(onClick = {
                    navigator.push(AuthScreen())
                    lyricist.languageTag = Locales.PT
                }) {
                    Text(strings.simple)
                }
                Button(
                    onClick = {
                        coroutine.launch {
                            settings.putString("test", "Salam: "+Random.nextInt())
                        }
                    }
                ) {
                    Text("Saved: ${test}")
                }
            }
        }

    }

}