package com.example.presentation.navigation

sealed class Routes(val route: String) {
    data object HomeMain : Routes("homeMain")

    data object FavouritesMain : Routes("favoritesMain")

    data object CourseDetail : Routes("course/{courseId}") {
        fun createRoute(courseId: Int) = "course/$courseId"
    }
}