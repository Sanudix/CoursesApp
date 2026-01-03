package com.example.domain.usecase

import com.example.domain.repository.CoursesRepository
import kotlinx.coroutines.flow.Flow

class GetFavouriteCourseIdsUseCase(
    private val repository: CoursesRepository
) {
    operator fun invoke(): Flow<Set<Int>> {
        return repository.getFavoriteCourseIds()
    }
}