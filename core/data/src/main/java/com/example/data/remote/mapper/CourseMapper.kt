package com.example.data.remote.mapper

import com.example.data.remote.dto.CourseDto
import com.example.domain.model.Course

fun CourseDto.toDomain(): Course {
    return Course(
        id = id,
        title = title,
        text = text,
        price = price,
        rate = rate,
        startDate = startDate,
        hasLike = hasLike,
        publishDate = publishDate
    )
}