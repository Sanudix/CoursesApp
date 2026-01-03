package com.example.favourites

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.components.CardItem
import com.example.presentation.FavouritesViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun FavouritesScreen(
    onCourseClick: (courseId: Int) -> Unit = {}
){
    val viewModel: FavouritesViewModel = koinViewModel()

    val favoriteCourses by viewModel.favoriteCourses.collectAsStateWithLifecycle()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color(0xff151515))
            .padding(top = 16.dp)
            .padding(horizontal = 16.dp)
    ) {
        Text(
            text = stringResource(R.string.favourites_favourites_label),
            color = Color(0xffF2F2F3),
            fontSize = 26.sp,
            fontFamily = FontFamily(Font(R.font.roboto)),
            fontWeight = FontWeight.Normal,
            textAlign = TextAlign.Start,
        )

        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ) {
            items(favoriteCourses) { course ->
                CardItem(
                    course = course,
                    onDetailsClick = {
                        onCourseClick(course.id)
                    },
                    onFavoriteClick = {
                        viewModel.toggleFavorite(course.id)
                    }
                )
            }
        }
    }
}