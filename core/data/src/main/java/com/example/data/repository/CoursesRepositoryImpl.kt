package com.example.data.repository

import com.example.data.remote.api.CoursesApi
import com.example.data.remote.mapper.toDomain
import com.example.domain.model.Course
import com.example.domain.repository.CoursesRepository

class CoursesRepositoryImpl(
    private val api: CoursesApi
) : CoursesRepository {

        override suspend fun getCourses(): List<Course> {
            return api
                .getCourses()
                .courses
                .map { it.toDomain() }
        }
}