package com.example.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.domain.utils.FormatDateFromJson

@Composable
fun DatePanel(
    text: String,
    modifier: Modifier = Modifier
        .wrapContentWidth()
        .height(24.dp),
    cornerRadius: Dp = 12.dp,
    backgroundColor: Color = Color(0xff32333A).copy(alpha = 0.7f)
) {
    val date = FormatDateFromJson()
    val formattedDate = date.formatDate(text)

    Box(
        modifier = modifier
            .clip(RoundedCornerShape(cornerRadius))
            .background(backgroundColor)
            .padding(horizontal = 6.dp, vertical = 4.dp)
    ) {
        Text(
            text = formattedDate,
            style = MaterialTheme.typography.bodySmall,
            fontFamily = FontFamily(Font(R.font.roboto)),
            color = Color(0xffF2F2F3)
        )
    }
}