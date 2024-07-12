package theme

import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import gps_tracker.composeapp.generated.resources.Res
import gps_tracker.composeapp.generated.resources.bold
import gps_tracker.composeapp.generated.resources.light
import gps_tracker.composeapp.generated.resources.medium
import gps_tracker.composeapp.generated.resources.regular
import gps_tracker.composeapp.generated.resources.semibold
import org.jetbrains.compose.resources.Font

@Composable
fun PoppinsFontFamily() = FontFamily(
    Font(Res.font.light, weight = FontWeight.Light),
    Font(Res.font.regular, weight = FontWeight.Normal),
    Font(Res.font.medium, weight = FontWeight.Medium),
    Font(Res.font.semibold, weight = FontWeight.SemiBold),
    Font(Res.font.bold, weight = FontWeight.Bold)
)