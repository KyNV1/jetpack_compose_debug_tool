package com.example.jetpack_compose_debug_tool
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog

@Composable
fun DebugDialog(
    onDismissRequest: () -> Unit
) {
    Dialog(onDismissRequest = onDismissRequest) {
        Card {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(text = "Debug Menu", style = androidx.compose.material3.MaterialTheme.typography.titleLarge)
                // TODO: Thêm các thông tin và chức năng debug của bạn vào đây
                Text(text = "Thông tin device: ...")
                Text(text = "Logs gần đây: ...")
                // v.v...
            }
        }
    }
}