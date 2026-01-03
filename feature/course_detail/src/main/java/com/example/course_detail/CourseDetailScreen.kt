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
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.components.DatePanel
import com.example.components.RatingPanel
import com.example.domain.model.Course
import com.example.presentation.SharedCoursesViewModel

@Composable
fun CourseDetailScreen(
    course: Course,
    sharedViewModel: SharedCoursesViewModel,
    onNavigateBack: () -> Unit = {},
    onStartCourse: () -> Unit = {},
    onGoToPlatform: () -> Unit = {}
) {
    val courses by sharedViewModel.courses.collectAsStateWithLifecycle()
    val isFavorite = courses.find { it.id == course.id }?.hasLike ?: course.hasLike

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color(0xff151515))
            .verticalScroll(rememberScrollState())
    ) {
        Box {
            Image(
                painter = painterResource(R.drawable.img_card_1_big),
                contentDescription = stringResource(R.string.course_detail_image),
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(240.dp)
            )

            Row {
                Box(
                    modifier = Modifier
                        .padding(top = 16.dp, start = 16.dp)
                        .clickable { onNavigateBack() }
                        .clip(CircleShape)
                        .background(Color(0xffF2F2F3))
                        .padding(8.dp)
                        .size(32.dp)
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
                        .padding(top = 16.dp, end = 16.dp)
                        .clickable {
                            sharedViewModel.toggleFavorite(course.id)
                        }
                        .clip(CircleShape)
                        .background(Color(0xffF2F2F3))
                        .padding(8.dp)
                        .size(32.dp)
                ) {
                    Icon(
                        painter = painterResource(
                            if (isFavorite) R.drawable.ic_favourites_filled
                            else R.drawable.ic_favourites_black
                        ),
                        contentDescription = if (isFavorite) stringResource(R.string.course_detail_remove_from_favourites)
                        else stringResource(R.string.course_detail_add_to_favourites),
                        modifier = Modifier
                            .size(24.dp)
                            .align(Alignment.Center),
                        tint = if (isFavorite) Color(0xff12B956) else Color.Black
                    )
                }
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 12.dp, top = 210.dp),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.Top
            ) {
                RatingPanel(
                    rating = course.rate.toFloatOrNull() ?: 0f,
                )

                Spacer(Modifier.width(4.dp))

                DatePanel(
                    text = course.startDate,
                )
            }
        }

        Column(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .padding(top = 16.dp, bottom = 24.dp)
        ) {
            Text(
                text = course.title,
                color = Color.White,
                fontSize = 22.sp,
                fontFamily = FontFamily(Font(R.font.roboto)),
                fontWeight = FontWeight.Normal,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Image(
                    painter = painterResource(R.drawable.img_author),
                    contentDescription = stringResource(R.string.course_detail_author),
                    modifier = Modifier.size(40.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Column {
                    Text(
                        text = stringResource(R.string.course_detail_author),
                        color = Color(0xffF2F2F3).copy(alpha = 0.5f),
                        fontSize = 14.sp,
                        fontFamily = FontFamily(Font(R.font.roboto))
                    )
                    Text(
                        text = stringResource(R.string.course_detail_author_name),
                        color = Color(0xffF2F2F3),
                        fontSize = 16.sp,
                        fontFamily = FontFamily(Font(R.font.roboto)),
                        fontWeight = FontWeight.Medium
                    )
                }
            }

            Spacer(modifier = Modifier.height(32.dp))

            Button(
                onClick = onStartCourse,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(44.dp),
                shape = RoundedCornerShape(100.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xff12B956)
                )
            ) {
                Text(
                    text = stringResource(R.string.course_detail_start_button),
                    fontSize = 15.sp,
                    fontFamily = FontFamily(Font(R.font.roboto)),
                    fontWeight = FontWeight.Normal,
                    color = Color(0xffF2F2F3)
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            Button(
                onClick = onGoToPlatform,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(44.dp),
                shape = RoundedCornerShape(100.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xff24252A)
                )
            ) {
                Text(
                    text = stringResource(R.string.course_detail_platform_button),
                    fontSize = 15.sp,
                    fontFamily = FontFamily(Font(R.font.roboto)),
                    fontWeight = FontWeight.Medium,
                    color = Color(0xffF2F2F3)
                )
            }

            Spacer(modifier = Modifier.height(28.dp))

            Text(
                text = stringResource(R.string.course_detail_about_title),
                color = Color.White,
                fontSize = 20.sp,
                fontFamily = FontFamily(Font(R.font.roboto)),
                fontWeight = FontWeight.Normal,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = course.text,
                color = Color(0xffF2F2F3).copy(alpha = 0.8f),
                fontSize = 16.sp,
                fontFamily = FontFamily(Font(R.font.roboto)),
                lineHeight = 24.sp,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = stringResource(R.string.course_detail_about_description),
                color = Color(0xffF2F2F3).copy(alpha = 0.8f),
                fontSize = 16.sp,
                fontFamily = FontFamily(Font(R.font.roboto)),
                lineHeight = 24.sp,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(28.dp))
            Text(
                text = stringResource(R.string.course_detail_price_format, course.price),
                color = Color(0xff12B956),
                fontSize = 20.sp,
                fontFamily = FontFamily(Font(R.font.roboto)),
                fontWeight = FontWeight.Bold,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}