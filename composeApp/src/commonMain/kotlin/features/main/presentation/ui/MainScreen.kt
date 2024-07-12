package features.main.presentation.ui

import TestViewModel
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import cafe.adriel.lyricist.ProvideStrings
import cafe.adriel.lyricist.rememberStrings
import cafe.adriel.lyricist.strings
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.koinNavigatorScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import cafe.adriel.voyager.navigator.tab.CurrentTab
import cafe.adriel.voyager.navigator.tab.TabNavigator
import com.russhwolf.settings.Settings
import core.locale.Locales
import features.auth.presentation.ui.AuthScreen
import features.main.domain.model.Tabs
import features.monitoring.presentation.ui.MonitoringTab
import gps_tracker.composeapp.generated.resources.Res
import gps_tracker.composeapp.generated.resources.compose_multiplatform
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.painterResource
import org.koin.compose.koinInject
import kotlin.random.Random

class MainScreen : Screen {
    @Composable
    override fun Content() {
        TabNavigator(MonitoringTab) {
            Scaffold(
                bottomBar = {
                    BottomBar()
                }
            ) {
                CurrentTab()
            }

        }

    }
}