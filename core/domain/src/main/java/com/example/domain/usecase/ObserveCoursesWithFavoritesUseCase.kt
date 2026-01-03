package com.example.domain.usecase

import com.example.domain.model.Course
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine

class ObserveCoursesWithFavoritesUseCase(
    private val getFavoriteCourseIdsUseCase: GetFavouriteCourseIdsUseCase
) {
    operator fun invoke(coursesFromNetwork: Flow<List<Course>>): Flow<List<Course>> {
        return combine(
            coursesFromNetwork,
            getFavoriteCourseIdsUseCase()
        ) { courses, favoriteIds ->
            mergeCourseWithFavorites(courses, favoriteIds)
        }
    }

    private fun mergeCourseWithFavorites(
        courses: List<Course>,
        favoriteIds: Set<Int>
    ): List<Course> {
        return courses.map { course ->
            course.copy(hasLike = course.id in favoriteIds)
        }
    }
}