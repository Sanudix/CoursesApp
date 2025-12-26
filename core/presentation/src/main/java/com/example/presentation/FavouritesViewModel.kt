package com.example.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.Course
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.SharingStarted

class FavouritesViewModel(
    coursesFlow: StateFlow<List<Course>>,
    private val onToggleFavorite: (Int) -> Unit
) : ViewModel() {

    val favoriteCourses: StateFlow<List<Course>> = coursesFlow
        .map { courses ->
            courses.filter { it.hasLike }
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    fun toggleFavorite(courseId: Int) {
        onToggleFavorite(courseId)
    }
}