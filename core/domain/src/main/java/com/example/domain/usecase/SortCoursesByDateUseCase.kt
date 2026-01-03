package com.example.domain.usecase

import com.example.domain.model.Course

class SortCoursesByDateUseCase {
    operator fun invoke(courses: List<Course>): List<Course> {
        return courses.sortedByDescending { it.publishDate }
    }
}