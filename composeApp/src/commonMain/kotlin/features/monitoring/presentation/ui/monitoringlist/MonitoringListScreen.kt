package features.monitoring.presentation.ui.monitoringlist

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.Text
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import features.main.presentation.ui.SearchBar
import features.main.presentation.ui.ToolBar
import kotlinx.coroutines.launch

class MonitoringListScreen: Screen {
    @OptIn(ExperimentalFoundationApi::class)
    @Composable
    override fun Content() {
        val pagerState = rememberPagerState { 2 }
        val coroutine = rememberCoroutineScope()
        Column(modifier = Modifier.fillMaxSize()) {
            ToolBar {
                Row(Modifier.fillMaxWidth().padding(16.dp)) {
                    SearchBar(
                        placeholder = "Search..."
                    )
                }
                TabRow(pagerState.currentPage) {
                    Tab(pagerState.currentPage == 0, onClick = {
                        coroutine.launch {
                            pagerState.scrollToPage(0)
                        }
                    }, text = {
                        Text("Units")
                    })
                    Tab(pagerState.currentPage == 1, onClick = {
                        coroutine.launch {
                            pagerState.scrollToPage(1)
                        }
                    }, text = {
                        Text("Groups")
                    })
                }
            }

            HorizontalPager(pagerState) { index->
                if(index == 0) {
                    Units()
                } else {
                    Groups()
                }
            }
        }
    }
}