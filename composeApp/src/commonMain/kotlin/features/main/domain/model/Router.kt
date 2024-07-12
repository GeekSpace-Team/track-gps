package features.main.domain.model

import features.geofence.presentation.ui.GeofencesTab
import features.main.presentation.ui.BottomBarItem
import features.map.presentation.ui.MapTab
import features.monitoring.presentation.ui.MonitoringTab
import features.notification.presentation.ui.NotificationTab
import features.report.presentation.ui.ReportTab
import features.settings.presentation.ui.SettingsTab
import features.status.presentation.ui.StatusTab
import gps_tracker.composeapp.generated.resources.Res
import gps_tracker.composeapp.generated.resources.geofences_active
import gps_tracker.composeapp.generated.resources.geofences_passive
import gps_tracker.composeapp.generated.resources.map_active
import gps_tracker.composeapp.generated.resources.map_passive
import gps_tracker.composeapp.generated.resources.monitoring_active
import gps_tracker.composeapp.generated.resources.monitoring_passive
import gps_tracker.composeapp.generated.resources.notification_active
import gps_tracker.composeapp.generated.resources.notification_passive
import gps_tracker.composeapp.generated.resources.report_active
import gps_tracker.composeapp.generated.resources.report_passive
import gps_tracker.composeapp.generated.resources.settings_active
import gps_tracker.composeapp.generated.resources.settings_passive
import gps_tracker.composeapp.generated.resources.status_active
import gps_tracker.composeapp.generated.resources.status_passive
import org.jetbrains.compose.resources.painterResource

sealed class BottomBarRouter(private val data: BottomBarItem) {
    class Monitoring(item: BottomBarItem): BottomBarRouter(item)
    class Map(item: BottomBarItem): BottomBarRouter(item)
    class Report(item: BottomBarItem): BottomBarRouter(item)
    class Notification(item: BottomBarItem): BottomBarRouter(item)
    class Geofences(item: BottomBarItem): BottomBarRouter(item)
    class Status(item: BottomBarItem): BottomBarRouter(item)
    class Settings(item: BottomBarItem): BottomBarRouter(item)

    fun getData(): BottomBarItem = data
}

val Tabs = listOf(
    BottomBarRouter.Monitoring(
        BottomBarItem(
            passiveIcon = Res.drawable.monitoring_passive,
            activeIcon = Res.drawable.monitoring_active,
            tab = MonitoringTab
        )
    ),
    BottomBarRouter.Map(
        BottomBarItem(
            passiveIcon = Res.drawable.map_passive,
            activeIcon = Res.drawable.map_active,
            tab = MapTab
        )
    ),
    BottomBarRouter.Report(
        BottomBarItem(
            passiveIcon = Res.drawable.report_passive,
            activeIcon = Res.drawable.report_active,
            tab = ReportTab
        )
    ),
    BottomBarRouter.Notification(
        BottomBarItem(
            passiveIcon = Res.drawable.notification_passive,
            activeIcon = Res.drawable.notification_active,
            tab = NotificationTab
        )
    ),
    BottomBarRouter.Geofences(
        BottomBarItem(
            passiveIcon = Res.drawable.geofences_passive,
            activeIcon = Res.drawable.geofences_active,
            tab = GeofencesTab
        )
    ),
    BottomBarRouter.Status(
        BottomBarItem(
            passiveIcon = Res.drawable.status_passive,
            activeIcon = Res.drawable.status_active,
            tab = StatusTab
        )
    ),
    BottomBarRouter.Settings(
        BottomBarItem(
            passiveIcon = Res.drawable.settings_passive,
            activeIcon = Res.drawable.settings_active,
            tab = SettingsTab
        )
    )
)