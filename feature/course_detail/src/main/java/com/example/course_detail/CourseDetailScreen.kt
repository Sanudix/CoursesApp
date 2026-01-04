package com.example.course_detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.components.DatePanel
import com.example.components.RatingPanel
import com.example.domain.model.Course
import com.example.presentation.SharedCoursesViewModel
import com.example.theme.ITCoursesApplicationTheme
import com.example.theme.dimensions

@Composable
fun CourseDetailScreen(
    course: Course,
    sharedViewModel: SharedCoursesViewModel,
    onNavigateBack: () -> Unit = {},
    onStartCourse: () -> Unit = {},
    onGoToPlatform: () -> Unit = {}
) {
    val colors = ITCoursesApplicationTheme.colors
    val dimensions = ITCoursesApplicationTheme.dimensions
    val courses by sharedViewModel.courses.collectAsStateWithLifecycle()
    val isFavorite = courses.find { it.id == course.id }?.hasLike ?: course.hasLike

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = colors.background)
            .verticalScroll(rememberScrollState())
    ) {
        Box {
            Image(
                painter = painterResource(R.drawable.img_card_1_big),
                contentDescription = stringResource(R.string.course_detail_image),
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(dimensions.detailImageHeight)
            )

            Row {
                Box(
                    modifier = Modifier
                        .padding(top = dimensions.paddingXMedium, start = dimensions.paddingXMedium)
                        .clickable { onNavigateBack() }
                        .clip(CircleShape)
                        .background(colors.textPrimary)
                        .padding(dimensions.paddingSmall)
                        .size(dimensions.iconSizeXLarge)
                ) {
                    Icon(
                        painter = painterResource(R.drawable.ic_arrow_left),
                        contentDescription = null,
                        modifier = Modifier.align(Alignment.Center),
                        tint = Color.Black
                    )
                }

                Spacer(Modifier.weight(1f))

                Box(
                    modifier = Modifier
                        .padding(top = dimensions.paddingXMedium, end = dimensions.paddingXMedium)
                        .clickable {
                            sharedViewModel.toggleFavorite(course.id)
                        }
                        .clip(CircleShape)
                        .background(colors.textPrimary)
                        .padding(dimensions.paddingSmall)
                        .size(dimensions.iconSizeXLarge)
                ) {
                    Icon(
                        painter = painterResource(
                            if (isFavorite) R.drawable.ic_favourites_filled
                            else R.drawable.ic_favourites_black
                        ),
                        contentDescription = if (isFavorite) stringResource(R.string.course_detail_remove_from_favourites)
                        else stringResource(R.string.course_detail_add_to_favourites),
                        modifier = Modifier
                            .size(dimensions.iconSizeLarge)
                            .align(Alignment.Center),
                        tint = if (isFavorite) colors.accent else Color.Black
                    )
                }
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = dimensions.cornerRadiusSmall, top = dimensions.rowHeightLarge),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.Top
            ) {
                RatingPanel(
                    rating = course.rate.toFloatOrNull() ?: 0f,
                )

                Spacer(Modifier.width(dimensions.paddingXXSmall))

                DatePanel(
                    text = course.startDate,
                )
            }
        }

        Column(
            modifier = Modifier
                .padding(horizontal = dimensions.paddingXMedium)
                .padding(top = dimensions.paddingXMedium, bottom = dimensions.paddingLarge)
        ) {
            Text(
                text = course.title,
                color = colors.textPrimary,
                style = MaterialTheme.typography.headlineSmall,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(dimensions.paddingXMedium))

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Image(
                    painter = painterResource(R.drawable.img_author),
                    contentDescription = stringResource(R.string.course_detail_author),
                    modifier = Modifier.size(dimensions.avatarSizeMedium)
                )
                Spacer(modifier = Modifier.width(dimensions.paddingSmall))
                Column {
                    Text(
                        text = stringResource(R.string.course_detail_author),
                        color = colors.textSecondary,
                        style = MaterialTheme.typography.bodySmall
                    )
                    Text(
                        text = stringResource(R.string.course_detail_author_name),
                        color = colors.textPrimary,
                        style = MaterialTheme.typography.bodyLarge,
                        fontWeight = FontWeight.Medium
                    )
                }
            }

            Spacer(modifier = Modifier.height(dimensions.paddingXXLarge))

            Button(
                onClick = onStartCourse,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(dimensions.buttonHeightMedium),
                shape = RoundedCornerShape(dimensions.cornerRadiusFull),
                colors = ButtonDefaults.buttonColors(
                    containerColor = colors.accent
                )
            ) {
                Text(
                    text = stringResource(R.string.course_detail_start_button),
                    style = MaterialTheme.typography.bodyMedium,
                    color = colors.textPrimary
                )
            }

            Spacer(modifier = Modifier.height(dimensions.paddingSmall))

            Button(
                onClick = onGoToPlatform,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(dimensions.buttonHeightMedium),
                shape = RoundedCornerShape(dimensions.cornerRadiusFull),
                colors = ButtonDefaults.buttonColors(
                    containerColor = colors.cardBackground
                )
            ) {
                Text(
                    text = stringResource(R.string.course_detail_platform_button),
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.Medium,
                    color = colors.textPrimary
                )
            }

            Spacer(modifier = Modifier.height(dimensions.paddingXLarge))

            Text(
                text = stringResource(R.string.course_detail_about_title),
                color = colors.textPrimary,
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(dimensions.paddingXMedium))

            Text(
                text = course.text,
                color = colors.textTertiary,
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(dimensions.paddingXMedium))

            Text(
                text = stringResource(R.string.course_detail_about_description),
                color = colors.textTertiary,
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(dimensions.paddingXLarge))

            Text(
                text = stringResource(R.string.course_detail_price_format, course.price),
                color = colors.accent,
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}