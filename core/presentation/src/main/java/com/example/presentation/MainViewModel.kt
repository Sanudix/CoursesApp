package com.example.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.Course
import com.example.domain.usecase.SortCoursesByDateUseCase
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.update

const val STOP_TIMEOUT_MILLIS = 5000L

class MainViewModel(
    private val sharedViewModel: SharedCoursesViewModel,
    private val sortCoursesByDateUseCase: SortCoursesByDateUseCase
) : ViewModel() {

    private val _showSorted = MutableStateFlow(false)
    val showSorted = _showSorted.asStateFlow()

    private val courses = sharedViewModel.courses

    val displayedCourses: StateFlow<List<Course>> = combine(
        courses,
        showSorted
    ) { courses, showSorted ->
        if (showSorted)
            sortCoursesByDateUseCase(courses)
        else
            courses
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(STOP_TIMEOUT_MILLIS),
        initialValue = emptyList()
    )

    val isLoading = sharedViewModel.isLoading
    val errorMessage = sharedViewModel.errorMessage

    fun toggleSorting() {
        _showSorted.update { !it }
    }

    fun toggleFavorite(courseId: Int) {
        sharedViewModel.toggleFavorite(courseId)
    }
}