package features.monitoring.presentation.ui

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import cafe.adriel.lyricist.strings
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions
import theme.PoppinsFontFamily

object MonitoringTab: Tab {
    @Composable
    override fun Content() {
        MonitoringRouter()
    }

    override val options: TabOptions
        @Composable
        get() {
            return TabOptions(
                index = 1U,
                title = strings.monitoring
            )
        }
}