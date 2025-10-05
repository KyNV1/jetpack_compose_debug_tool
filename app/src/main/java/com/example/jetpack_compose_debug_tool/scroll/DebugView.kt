package com.example.jetpack_compose_debug_tool.scroll

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ModifierLocalBeyondBoundsLayout
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun DebugView(
    modifier: Modifier = Modifier
) {
    Column (
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(32.dp)
    ){

    }
}

@Composable
fun CustomStyledTextField(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
    ){

        Text(
            text = "REFRESH TOKEN",
            modifier = Modifier.padding(start = 8.dp, bottom = 8.dp), // Khoảng cách giữa nhãn và text field
            style = TextStyle(
                color = Color.DarkGray,
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                letterSpacing = 0.5.sp // Tăng khoảng cách giữa các chữ cái một chút
            )
        )

        // 1. Quản lý trạng thái cho văn bản nhập vào
        var text by remember { mutableStateOf("") }
        BasicTextField(
            value = text,
            onValueChange = { newText ->
                text = newText
            },
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    color = Color(0xFFF1EFF2),
                    shape = RoundedCornerShape(12.dp)
                )
                .padding(horizontal = 16.dp, vertical = 14.dp),

            textStyle = TextStyle(
                color = Color.Black,
                fontSize = 16.sp
            ),

            singleLine = true
        )
    }

}

// Hàm Preview để xem trước giao diện trong Android Studio
@Preview(showBackground = true)
@Composable
fun CustomTextFieldPreview() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFE4E0E5))
            .padding(20.dp),
        contentAlignment = Alignment.Center
    ) {
        CustomStyledTextField()
    }

}