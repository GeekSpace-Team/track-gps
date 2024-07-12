package features.main.presentation.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun ToolBar(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Column(modifier = modifier.fillMaxWidth().background(
        color = MaterialTheme.colors.primary
    )) {
        content()
    }
}

@Composable
fun SearchBar(
    modifier: Modifier = Modifier,
    placeholder: String
) {
    val query = rememberSaveable {
        mutableStateOf("")
    }
    TextField(
        value = query.value,
        onValueChange = { query.value = it },
        placeholder = {
            Text(placeholder)
        },
        shape = RoundedCornerShape(2.dp),
        modifier = modifier.background(
            color = MaterialTheme.colors.surface
        ).padding(6.dp),
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = Color.Transparent
        )
    )
}