package com.example.domain.usecase

import com.example.domain.repository.CoursesRepository

class ToggleFavouriteUseCase(
    private val repository: CoursesRepository
) {
    suspend operator fun invoke(courseId: Int) {
        repository.toggleFavorite(courseId)
    }
}