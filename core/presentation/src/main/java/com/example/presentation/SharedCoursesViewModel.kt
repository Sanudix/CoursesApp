package com.example.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.Course
import com.example.domain.repository.CoursesRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SharedCoursesViewModel(
    private val repository: CoursesRepository
) : ViewModel() {

    private val _courses = MutableStateFlow<List<Course>>(emptyList())
    val courses: StateFlow<List<Course>> = _courses.asStateFlow()

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
                _courses.value = repository.getCourses()
            } catch (e: Exception) {
                _errorMessage.value = e.message ?: "Ошибка загрузки курсов"
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun toggleFavorite(courseId: Int) {
        _courses.update { list ->
            list.map {
                if (it.id == courseId)
                    it.copy(hasLike = !it.hasLike)
                else it
            }
        }
    }
}