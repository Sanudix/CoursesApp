package com.example.data.repository

import com.example.data.local.dao.FavouriteCourseDao
import com.example.data.local.entity.FavouriteCourseEntity
import com.example.data.remote.api.CoursesApi
import com.example.data.remote.mapper.toDomain
import com.example.domain.model.Course
import com.example.domain.repository.CoursesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class CoursesRepositoryImpl(
    private val api: CoursesApi,
    private val favouriteCourseDao: FavouriteCourseDao
) : CoursesRepository {

    override suspend fun getCourses(): List<Course> {
        return api
            .getCourses()
            .courses
            .map { it.toDomain() }
    }

    override suspend fun toggleFavorite(courseId: Int) {
        if (isFavorite(courseId)) {
            favouriteCourseDao.deleteFavorite(courseId)
        } else {
            favouriteCourseDao.insertFavorite(FavouriteCourseEntity(courseId))
        }
    }

    override suspend fun isFavorite(courseId: Int): Boolean {
        return favouriteCourseDao.isFavorite(courseId)
    }

    override fun getFavoriteCourseIds(): Flow<Set<Int>> {
        return favouriteCourseDao.getAllFavorites()
            .map { favorites -> favorites.map { it.courseId }.toSet() }
    }
}