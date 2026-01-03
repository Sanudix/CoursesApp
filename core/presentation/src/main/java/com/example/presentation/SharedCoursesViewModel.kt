package com.example.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.Course
import com.example.domain.usecase.GetCoursesUseCase
import com.example.domain.usecase.ObserveCoursesWithFavoritesUseCase
import com.example.domain.usecase.ToggleFavouriteUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class SharedCoursesViewModel(
    private val getCoursesUseCase: GetCoursesUseCase,
    observeCoursesWithFavoritesUseCase: ObserveCoursesWithFavoritesUseCase,
    private val toggleFavoriteUseCase: ToggleFavouriteUseCase
) : ViewModel() {

    private val _coursesFromNetwork = MutableStateFlow<List<Course>>(emptyList())

    val courses: StateFlow<List<Course>> = observeCoursesWithFavoritesUseCase(
        coursesFromNetwork = _coursesFromNetwork
    ).stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = emptyList()
    )

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage.asStateFlow()

    init {
        loadCourses()
    }

    fun loadCourses() {
        viewModelScope.launch {
            _isLoading.value = true
            _errorMessage.value = null

            try {
                val courses = getCoursesUseCase()
                _coursesFromNetwork.value = courses
            } catch (e: Exception) {
                _errorMessage.value = e.message ?: "Ошибка загрузки курсов"
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun toggleFavorite(courseId: Int) {
        viewModelScope.launch {
            try {
                toggleFavoriteUseCase(courseId)
            } catch (e: Exception) {
                _errorMessage.value = "Ошибка обновления избранного"
            }
        }
    }
}