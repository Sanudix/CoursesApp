package com.example.components


import com.example.theme.ITCoursesApplicationTheme
import com.example.theme.dimensions
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
import androidx.compose.ui.unit.Dp
import com.example.domain.utils.FormatDateFromJson

@Composable
fun DatePanel(
    text: String,
    modifier: Modifier = Modifier
        .wrapContentWidth()
        .height(ITCoursesApplicationTheme.dimensions.datePanelHeight),
    cornerRadius: Dp = ITCoursesApplicationTheme.dimensions.cornerRadiusSmall,
    backgroundColor: Color = ITCoursesApplicationTheme.colors.overlay
) {
    val date = FormatDateFromJson()
    val formattedDate = date.formatDate(text)
    val colors = ITCoursesApplicationTheme.colors
    val dimensions = ITCoursesApplicationTheme.dimensions

    Box(
        modifier = modifier
            .clip(RoundedCornerShape(cornerRadius))
            .background(backgroundColor)
            .padding(
                horizontal = dimensions.paddingXSmall,
                vertical = dimensions.paddingXXSmall
            )
    ) {
        Text(
            text = formattedDate,
            style = MaterialTheme.typography.bodySmall,
            color = colors.textPrimary
        )
    }
}