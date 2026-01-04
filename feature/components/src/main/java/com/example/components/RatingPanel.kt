package com.example.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import com.example.theme.ITCoursesApplicationTheme
import com.example.theme.dimensions

@Composable
fun RatingPanel(
    rating: Float,
    modifier: Modifier = Modifier.width(ITCoursesApplicationTheme.dimensions.ratingPanelWidth),
    cornerRadius: Dp = ITCoursesApplicationTheme.dimensions.cornerRadiusSmall,
    backgroundColor: Color = ITCoursesApplicationTheme.colors.overlay
) {
    val colors = ITCoursesApplicationTheme.colors
    val dimensions = ITCoursesApplicationTheme.dimensions

    Box(
        modifier = modifier
            .clip(RoundedCornerShape(cornerRadius))
            .background(backgroundColor)
            .padding(vertical = dimensions.paddingXXSmall)
            .padding(start = dimensions.paddingXSmall)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                painter = painterResource(R.drawable.ic_star),
                contentDescription = null,
                tint = colors.accent,
                modifier = Modifier.size(dimensions.iconSizeSmall)
            )
            Spacer(modifier = Modifier.width(dimensions.paddingXXSmall))
            Text(
                text = rating.toString(),
                style = MaterialTheme.typography.bodySmall,
                fontWeight = FontWeight.Medium,
                color = colors.textPrimary
            )
        }
    }
}