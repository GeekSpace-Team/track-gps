package features.main.presentation.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.navigator.tab.LocalTabNavigator
import cafe.adriel.voyager.navigator.tab.Tab
import features.main.domain.model.Tabs
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource
import theme.PoppinsFontFamily

@Composable
fun BottomBar(
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.fillMaxWidth().background(MaterialTheme.colors.surface)
            .border(
                0.2.dp,
                color = MaterialTheme.colors.onSurface.copy(alpha = 0.5f)
            )
            .horizontalScroll(
                rememberScrollState()
            ),
        verticalAlignment = Alignment.CenterVertically
    ) {
        repeat(Tabs.count()) { index ->
            BottomBarItemUi(Tabs[index].getData())
        }
    }
}

@Composable
fun BottomBarItemUi(item: BottomBarItem) {
    val currentTab = LocalTabNavigator.current
    val active = item.tab == currentTab.current
    val color = if (active) MaterialTheme.colors.primary else MaterialTheme.colors.onSurface
    Column(
        modifier = Modifier
            .clip(RoundedCornerShape(12.dp))
            .width(110.dp)
            .height(75.dp)
            .padding(6.dp)
            .clickable {
                currentTab.current = item.tab
            }.padding(6.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            painter = painterResource(if (active) item.activeIcon else item.passiveIcon),
            contentDescription = item.tab.options.title,
            modifier = Modifier.size(24.dp),
            tint = color
        )
        Text(
            item.tab.options.title,
            color = color, maxLines = 1, overflow = TextOverflow.Ellipsis,
            style = MaterialTheme.typography.caption.copy(
                fontSize = 14.sp,
                fontFamily = PoppinsFontFamily()
            )
        )
    }
}

data class BottomBarItem(
    val passiveIcon: DrawableResource,
    val activeIcon: DrawableResource,
    val tab: Tab
)