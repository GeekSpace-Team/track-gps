package core.locale

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import cafe.adriel.lyricist.LyricistStrings

@LyricistStrings(languageTag = Locales.EN, default = true)
internal val EnStrings = Strings(
    monitoring = "Monitoring",
    map = "Map",
    reports = "Reports",
    notifications = "Notification",
    geofences = "Geofences",
    status = "Status",
    settings = "Settings"
)