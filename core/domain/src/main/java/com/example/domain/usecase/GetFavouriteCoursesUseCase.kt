package com.example.domain.usecase

import com.example.domain.model.Course

class GetFavouriteCoursesUseCase {
    operator fun invoke(courses: List<Course>): List<Course> {
        return courses.filter { it.hasLike }
    }
}