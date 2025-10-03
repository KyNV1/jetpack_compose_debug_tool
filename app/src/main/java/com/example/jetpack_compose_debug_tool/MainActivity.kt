package com.example.jetpack_compose_debug_tool

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.jetpack_compose_debug_tool.debug.DebugSheetManager
import com.example.jetpack_compose_debug_tool.ui.theme.Jetpack_compose_debug_toolTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Jetpack_compose_debug_toolTheme {

                // Box đóng vai trò là container cho cả app và debug tool
                Box(modifier = Modifier.fillMaxSize().padding()) {
                    // Hiển thị giao diện chính của ứng dụng
                    MainAppContent()

                    // CHỈ KÍCH HOẠT DEBUG TOOL TRONG MÔI TRƯỜNG DEBUG

                    // Code này sẽ bị loại bỏ hoàn toàn trong bản build release
                    if (BuildConfig.DEBUG) {
                        DebugSheetManager()
                    }
                }
            }
        }
    }
}

@Composable
fun MainAppContent() {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Box(contentAlignment = Alignment.Center) {
            Text("Lắc điện thoại để mở menu debug!")
        }
    }
}

