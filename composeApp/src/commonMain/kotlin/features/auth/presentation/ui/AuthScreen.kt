package features.auth.presentation.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.core.screen.Screen
import com.multiplatform.webview.web.WebView
import com.multiplatform.webview.web.rememberWebViewState

class AuthScreen : Screen {
    @Composable
    override fun Content() {
        AuthUI()
    }
}

@Composable
fun AuthUI() {
    val webViewState =
        rememberWebViewState("https://gps.ytm.tm/login.html")
    Column(Modifier.fillMaxSize()) {
        val text = webViewState.let {
            "${it.pageTitle ?: ""} ${it.loadingState} ${it.lastLoadedUrl ?: ""}"
        }
        Text(text)
        WebView(
            state = webViewState,
            modifier = Modifier.fillMaxSize()
        )
    }
}