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
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.components.CardItem
import com.example.presentation.MainViewModel
import com.example.theme.ITCoursesApplicationTheme
import com.example.theme.dimensions
import org.koin.androidx.compose.koinViewModel

@Composable
fun MainScreen(
    onCourseClick: (courseId: Int) -> Unit = {}
) {
    val viewModel: MainViewModel = koinViewModel()
    val colors = ITCoursesApplicationTheme.colors
    val dimensions = ITCoursesApplicationTheme.dimensions
    val courses by viewModel.displayedCourses.collectAsStateWithLifecycle()
    val isLoading by viewModel.isLoading.collectAsStateWithLifecycle()
    val errorMessage by viewModel.errorMessage.collectAsStateWithLifecycle()

    var searchQuery by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = colors.background)
            .padding(top = dimensions.paddingXMedium)
            .padding(horizontal = dimensions.paddingXMedium)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Box(
                modifier = Modifier
                    .weight(1f)
                    .height(dimensions.buttonHeightLarge)
                    .shadow(
                        elevation = dimensions.paddingSmall,
                        shape = RoundedCornerShape(dimensions.cornerRadiusLarge),
                        clip = true
                    )
                    .background(colors.cardBackground)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Icon(
                        painter = painterResource(R.drawable.ic_search),
                        contentDescription = null,
                        tint = colors.textPrimary,
                        modifier = Modifier
                            .padding(
                                start = dimensions.paddingXMedium,
                                top = dimensions.paddingXMedium,
                                bottom = dimensions.paddingXMedium
                            )
                    )

                    TextField(
                        value = searchQuery,
                        onValueChange = { newText -> searchQuery = newText },
                        placeholder = {
                            Text(
                                text = stringResource(R.string.main_search_placeholder),
                                style = MaterialTheme.typography.bodyLarge,
                                color = colors.textSecondary
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

            Spacer(Modifier.width(dimensions.paddingSmall))

            Box(
                modifier = Modifier
                    .background(
                        shape = CircleShape,
                        color = colors.cardBackground
                    )
                    .size(dimensions.avatarSizeLarge),
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_funnel),
                    contentDescription = null,
                    modifier = Modifier.align(Alignment.Center),
                    tint = colors.textPrimary
                )
            }
        }

        Spacer(Modifier.height(dimensions.paddingXMedium))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { viewModel.toggleSorting() },
            horizontalArrangement = Arrangement.End,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(R.string.main_sort_by_date),
                color = colors.accent,
                style = MaterialTheme.typography.labelLarge,
                textAlign = TextAlign.Start
            )

            Spacer(Modifier.width(dimensions.dividerHeight))

            Icon(
                painter = painterResource(R.drawable.ic_arrow_down_up),
                contentDescription = null,
                tint = colors.accent
            )
        }

        when {
            isLoading -> {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(vertical = dimensions.paddingXXLarge),
                    contentAlignment = Alignment.Center
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(dimensions.paddingXMedium)
                    ) {
                        CircularProgressIndicator(color = colors.accent)
                        Text(
                            text = stringResource(R.string.main_loading),
                            color = colors.textPrimary,
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                }
            }

            errorMessage != null -> {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(vertical = dimensions.paddingXXLarge),
                    contentAlignment = Alignment.Center
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(dimensions.paddingXMedium)
                    ) {
                        Text(
                            text = stringResource(R.string.main_error_title),
                            color = colors.error,
                            style = MaterialTheme.typography.titleMedium
                        )
                        Text(
                            text = errorMessage ?: stringResource(R.string.main_error_unknown),
                            color = colors.textPrimary,
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                }
            }

            courses.isEmpty() -> {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(vertical = dimensions.paddingXXLarge),
                    contentAlignment = Alignment.Center
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(dimensions.paddingXMedium)
                    ) {
                        Text(
                            text = stringResource(R.string.main_no_courses),
                            color = colors.textPrimary,
                            style = MaterialTheme.typography.titleMedium
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