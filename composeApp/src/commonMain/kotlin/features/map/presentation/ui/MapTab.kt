package features.map.presentation.ui

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import cafe.adriel.lyricist.strings
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions

object MapTab: Tab {
    @Composable
    override fun Content() {
        Text("Map")
    }

    override val options: TabOptions
        @Composable
        get() {
            return TabOptions(
                index = 2U,
                title = strings.map
            )
        }
}