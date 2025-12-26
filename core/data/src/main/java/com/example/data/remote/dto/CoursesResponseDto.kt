package com.example.data.remote.dto

import com.google.gson.annotations.SerializedName

data class CoursesResponseDto(
    @SerializedName("courses")
    val courses: List<CourseDto>
)