package features.monitoring.presentation.ui

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.navigator.Navigator
import features.monitoring.presentation.ui.monitoringlist.MonitoringListScreen

@Composable
fun MonitoringRouter() {
    Navigator(MonitoringListScreen())
}