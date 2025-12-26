package com.example.main

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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.components.CardItem
import com.example.presentation.MainViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun MainScreen(
    onCourseClick: (courseId: Int) -> Unit = {}
) {
    val viewModel: MainViewModel = koinViewModel()
    val courses by viewModel.displayedCourses.collectAsStateWithLifecycle()
    val isLoading by viewModel.isLoading.collectAsStateWithLifecycle()
    val errorMessage by viewModel.errorMessage.collectAsStateWithLifecycle()

    var searchQuery by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color(0xff151515))
            .padding(top = 16.dp)
            .padding(horizontal = 16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Box(
                modifier = Modifier
                    .weight(1f)
                    .height(52.dp)
                    .shadow(
                        elevation = 8.dp,
                        shape = RoundedCornerShape(28.dp),
                        clip = true
                    )
                    .background(Color(0xff24252A))
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Icon(
                        painter = painterResource(R.drawable.ic_search),
                        contentDescription = null,
                        tint = Color(0xffF2F2F3),
                        modifier = Modifier
                            .padding(start = 16.dp, top = 16.dp, bottom = 16.dp)
                    )

                    TextField(
                        value = searchQuery,
                        onValueChange = { newText -> searchQuery = newText },
                        placeholder = {
                            Text(
                                "Search courses...",
                                fontFamily = FontFamily(Font(R.font.roboto)),
                                fontSize = 18.sp,
                                color = Color(0xffF2F2F3),
                                modifier = Modifier.alpha(0.5f)
                            )
                        },
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Text,
                            imeAction = ImeAction.Done,
                            autoCorrect = false,
                            capitalization = KeyboardCapitalization.None,
                        ),
                        colors = TextFieldDefaults.colors(
                            focusedContainerColor = Color.Transparent,
                            unfocusedContainerColor = Color.Transparent,
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent,
                        ),
                        modifier = Modifier.fillMaxWidth(),
                    )
                }
            }

            Spacer(Modifier.width(8.dp))

            Box(
                modifier = Modifier
                    .background(
                        shape = CircleShape,
                        color = Color(0xff24252A)
                    )
                    .size(56.dp),
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_funnel),
                    contentDescription = null,
                    modifier = Modifier.align(Alignment.Center),
                    tint = Color(0xffF2F2F3)
                )
            }
        }

        Spacer(Modifier.height(16.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable{viewModel.toggleSorting()},
            horizontalArrangement = Arrangement.End,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "По дате добавления",
                color = Color(0xff12B956),
                fontSize = 14.sp,
                fontFamily = FontFamily(Font(R.font.roboto)),
                fontWeight = FontWeight.SemiBold,
                textAlign = TextAlign.Start
            )

            Spacer(Modifier.width(2.dp))

            Icon(
                painter = painterResource(R.drawable.ic_arrow_down_up),
                contentDescription = null,
                tint = Color(0xff12B956)
            )
        }

        when {
            isLoading -> {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(vertical = 32.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        CircularProgressIndicator(color = Color(0xff12B956))
                        Text(
                            text = "Загрузка курсов...",
                            color = Color(0xffF2F2F3),
                            fontFamily = FontFamily(Font(R.font.roboto))
                        )
                    }
                }
            }

            errorMessage != null -> {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(vertical = 32.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        Text(
                            text = "Ошибка загрузки",
                            color = Color(0xffFF5252),
                            fontFamily = FontFamily(Font(R.font.roboto)),
                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp
                        )
                        Text(
                            text = errorMessage ?: "Неизвестная ошибка",
                            color = Color(0xffF2F2F3),
                            fontFamily = FontFamily(Font(R.font.roboto))
                        )
                    }
                }
            }

            courses.isEmpty() -> {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(vertical = 32.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        Text(
                            text = "Курсы не найдены",
                            color = Color(0xffF2F2F3),
                            fontFamily = FontFamily(Font(R.font.roboto)),
                            fontSize = 18.sp
                        )
                    }
                }
            }

            else -> {
                LazyColumn(
                    modifier = Modifier.fillMaxSize()
                ) {
                    items(courses) { course ->
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
    }
}