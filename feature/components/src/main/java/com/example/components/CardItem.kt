package com.example.components
//
//import androidx.compose.foundation.Image
//import androidx.compose.foundation.background
//import androidx.compose.foundation.layout.Arrangement
//import androidx.compose.foundation.layout.Box
//import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.Row
//import androidx.compose.foundation.layout.Spacer
//import androidx.compose.foundation.layout.fillMaxWidth
//import androidx.compose.foundation.layout.height
//import androidx.compose.foundation.layout.padding
//import androidx.compose.foundation.layout.size
//import androidx.compose.foundation.layout.width
//import androidx.compose.foundation.shape.CircleShape
//import androidx.compose.foundation.shape.RoundedCornerShape
//import androidx.compose.material3.ButtonDefaults
//import androidx.compose.material3.Card
//import androidx.compose.material3.CardDefaults
//import androidx.compose.material3.Icon
//import androidx.compose.material3.IconButton
//import androidx.compose.material3.MaterialTheme
//import androidx.compose.material3.Text
//import androidx.compose.material3.TextButton
//import androidx.compose.runtime.Composable
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.draw.alpha
//import androidx.compose.ui.draw.clip
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.layout.ContentScale
//import androidx.compose.ui.res.painterResource
//import androidx.compose.ui.res.stringResource
//import androidx.compose.ui.text.font.Font
//import androidx.compose.ui.text.font.FontFamily
//import androidx.compose.ui.text.font.FontWeight
//import androidx.compose.ui.text.style.TextOverflow
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.unit.sp
//import com.example.domain.model.Course
//import com.example.domain.utils.FormatDateFromJson
//
//
//@Composable
//fun CardItem(
//    course: Course,
//    onDetailsClick: () -> Unit = {},
//    onFavoriteClick: (Course) -> Unit = {},
//) {
//    val dateFormatter = FormatDateFromJson()
//
//    Card(
//        modifier = Modifier
//            .fillMaxWidth()
//            .clip(RoundedCornerShape(16.dp))
//            .padding(top = 16.dp),
//        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
//        shape = MaterialTheme.shapes.medium,
//        colors = CardDefaults.cardColors(containerColor = Color(0xff24252A)),
//    ) {
//        Box {
//            Image(
//                painter = painterResource(R.drawable.img_card_1),
//                contentDescription = null,
//                contentScale = ContentScale.Crop,
//                alignment = Alignment.TopCenter,
//
//                modifier = Modifier
//                    .clip(RoundedCornerShape(16.dp))
//                    .fillMaxWidth()
//                    .height(132.dp)
//            )
//
//            Row(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(start = 12.dp, top = 100.dp, end = 12.dp),
//                horizontalArrangement = Arrangement.Start,
//                verticalAlignment = Alignment.Top
//            ) {
//                RatingPanel(
//                    rating = course.rate.toFloatOrNull() ?: 0f,
//                )
//
//                Spacer(Modifier.width(4.dp))
//
//                DatePanel(
//                    text = dateFormatter.formatDate(course.startDate),
//                )
//            }
//
//            IconButton(
//                onClick = { onFavoriteClick(course) },
//                modifier = Modifier
//                    .padding(top = 20.dp, end = 20.dp)
//                    .align(Alignment.TopEnd)
//                    .size(24.dp)
//                    .clip(CircleShape)
//                    .background(Color(0xff32333A).copy(alpha = 0.7f))
//            ) {
//                Icon(
//                    painter = painterResource(
//                        if (course.hasLike) R.drawable.ic_favourites_filled
//                        else R.drawable.ic_favourites
//                    ),
//                    contentDescription = if (course.hasLike) stringResource(R.string.components_delete_from_favourites)
//                    else stringResource(R.string.components_add_to_favourites),
//                    tint = if (course.hasLike) Color(0xff12B956) else Color(0xffF2F2F3),
//                    modifier = Modifier.size(17.dp)
//                )
//            }
//        }
//
//        Column(
//            modifier = Modifier.padding(16.dp)
//        ) {
//            Text(
//                text = course.title,
//                fontFamily = FontFamily(Font(R.font.roboto)),
//                fontWeight = FontWeight.Bold,
//                fontSize = 16.sp,
//                color = Color(0xffF2F2F3),
//                modifier = Modifier.fillMaxWidth()
//            )
//
//            Spacer(modifier = Modifier.height(8.dp))
//
//            Text(
//                text = course.text,
//                style = MaterialTheme.typography.bodyMedium,
//                fontFamily = FontFamily(Font(R.font.roboto)),
//                maxLines = 2,
//                overflow = TextOverflow.Ellipsis,
//                color = Color(0xffF2F2F3),
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .alpha(0.4f)
//            )
//
//            Spacer(modifier = Modifier.height(6.dp))
//
//            Row(
//                modifier = Modifier.fillMaxWidth(),
//                horizontalArrangement = Arrangement.SpaceBetween,
//                verticalAlignment = Alignment.CenterVertically
//            ) {
//                Text(
//                    text = stringResource(R.string.components_price_format, course.price),
//                    style = MaterialTheme.typography.titleLarge,
//                    fontWeight = FontWeight.Bold,
//                    fontSize = 18.sp,
//                    color = Color(0xffF2F2F3),
//                )
//
//                TextButton(
//                    onClick = {
//                        onDetailsClick()
//                    },
//                    colors = ButtonDefaults.textButtonColors(
//                        contentColor = Color(0xff12B956)
//                    )
//                ) {
//                    Text(
//                        text = stringResource(R.string.components_more_button),
//                        style = MaterialTheme.typography.bodyMedium,
//                        fontWeight = FontWeight.Medium,
//                        color = Color(0xff12B956)
//                    )
//                }
//            }
//        }
//    }
//}

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import com.example.domain.model.Course
import com.example.domain.utils.FormatDateFromJson
import com.example.theme.ITCoursesApplicationTheme
import com.example.theme.dimensions

@Composable
fun CardItem(
    course: Course,
    onDetailsClick: () -> Unit = {},
    onFavoriteClick: (Course) -> Unit = {},
) {
    val dateFormatter = FormatDateFromJson()
    val colors = ITCoursesApplicationTheme.colors
    val dimensions = ITCoursesApplicationTheme.dimensions

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(dimensions.cornerRadiusMedium))
            .padding(top = dimensions.paddingXMedium),
        elevation = CardDefaults.cardElevation(defaultElevation = dimensions.paddingXXSmall),
        shape = MaterialTheme.shapes.medium,
        colors = CardDefaults.cardColors(containerColor = colors.cardBackground),
    ) {
        Box {
            Image(
                painter = painterResource(R.drawable.img_card_1),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                alignment = Alignment.TopCenter,
                modifier = Modifier
                    .clip(RoundedCornerShape(dimensions.cornerRadiusMedium))
                    .fillMaxWidth()
                    .height(dimensions.cardImageHeight)
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        start = dimensions.cornerRadiusSmall,
                        top = dimensions.rowHeightMedium,
                        end = dimensions.cornerRadiusSmall
                    ),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.Top
            ) {
                RatingPanel(
                    rating = course.rate.toFloatOrNull() ?: 0f,
                )

                Spacer(Modifier.width(dimensions.paddingXXSmall))

                DatePanel(
                    text = dateFormatter.formatDate(course.startDate),
                )
            }

            IconButton(
                onClick = { onFavoriteClick(course) },
                modifier = Modifier
                    .padding(top = dimensions.paddingMedium, end = dimensions.paddingMedium)
                    .align(Alignment.TopEnd)
                    .size(dimensions.iconSizeLarge)
                    .clip(CircleShape)
                    .background(colors.overlay)
            ) {
                Icon(
                    painter = painterResource(
                        if (course.hasLike) R.drawable.ic_favourites_filled
                        else R.drawable.ic_favourites
                    ),
                    contentDescription = if (course.hasLike) stringResource(R.string.components_delete_from_favourites)
                    else stringResource(R.string.components_add_to_favourites),
                    tint = if (course.hasLike) colors.accent else colors.textPrimary,
                    modifier = Modifier.size(dimensions.iconSizeMedium)
                )
            }
        }

        Column(
            modifier = Modifier.padding(dimensions.paddingXMedium)
        ) {
            Text(
                text = course.title,
                style = MaterialTheme.typography.titleSmall,
                color = colors.textPrimary,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(dimensions.paddingSmall))

            Text(
                text = course.text,
                style = MaterialTheme.typography.bodyMedium,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                color = colors.textMuted,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(dimensions.paddingXSmall))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = stringResource(R.string.components_price_format, course.price),
                    style = MaterialTheme.typography.titleMedium,
                    color = colors.textPrimary,
                )

                TextButton(
                    onClick = { onDetailsClick() },
                    colors = ButtonDefaults.textButtonColors(
                        contentColor = colors.accent
                    )
                ) {
                    Text(
                        text = stringResource(R.string.components_more_button),
                        style = MaterialTheme.typography.bodyMedium,
                        fontWeight = FontWeight.Medium,
                        color = colors.accent
                    )
                }
            }
        }
    }
}