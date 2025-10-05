package com.example.jetpack_compose_debug_tool.scroll

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jetpack_compose_debug_tool.data.SocialLinkInfo
import com.example.jetpack_compose_debug_tool.data.dummySocialData

@Composable
fun DebugView(
    modifier: Modifier = Modifier
) {
    var socialLinks by remember { mutableStateOf(dummySocialData) }
    val context = LocalContext.current

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFFE4E0E5))
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(32.dp)
    ) {
        itemsIndexed(
            items = socialLinks,
            // 3. Cung cấp key để tối ưu hiệu năng
            key = { _, item -> item.id }
        ) { index, item ->
            SocialItem(
                item = item,
                onValueChange = { newValue ->
                    // 4. Khi giá trị của một item thay đổi, tạo ra một danh sách mới
                    // và cập nhật lại state để Compose recompose
                    val newList = socialLinks.toMutableList()
                    newList[index] = item.copy(value = newValue)
                    socialLinks = newList
                },
                onLinkClick = {
                    // Xử lý sự kiện khi link được click
                    Toast.makeText(
                        context,
                        "Link ${item.url} clicked!",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            )

        }

        item {
            Text(text = "afsdfs")
        }

    }
}


@Composable
fun SocialItem(
    modifier: Modifier = Modifier,
    item: SocialLinkInfo,
    onValueChange: (String) -> Unit,
    onLinkClick: () -> Unit,
) {
    val uriHandler = LocalUriHandler.current
    Column(modifier = modifier) {
        val annotatedString = buildAnnotatedString {
            withStyle(style = SpanStyle(fontWeight = FontWeight.Bold, color = Color.DarkGray)) {
                append(item.label.uppercase())
            }
            pushStringAnnotation(tag = "ACTION_TAG", annotation = "click")
            withStyle(style = SpanStyle(color = Color(0xFF007BFF))) {
                append(item.url)
            }
            pop()
        }

        ClickableText(
            text = annotatedString,
            onClick = { offset ->
                annotatedString.getStringAnnotations(tag = "ACTION_TAG", start = offset, end = offset)
                    .firstOrNull()?.let {
                       onLinkClick()
                    }
            },
            modifier = Modifier.padding(bottom = 8.dp)
        )

        BasicTextField(
            value = item.value,
            onValueChange = onValueChange,
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