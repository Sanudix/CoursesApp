package com.example.domain.repository

import com.example.domain.model.Course
import kotlinx.coroutines.flow.Flow

interface CoursesRepository {
    suspend fun getCourses(): List<Course>
    suspend fun toggleFavorite(courseId: Int)
    suspend fun isFavorite(courseId: Int): Boolean
    fun getFavoriteCourseIds(): Flow<Set<Int>>
}