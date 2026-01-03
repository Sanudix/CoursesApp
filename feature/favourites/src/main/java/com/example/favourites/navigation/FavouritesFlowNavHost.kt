package com.example.favourites.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.course_detail.CourseDetailScreen
import com.example.favourites.FavouritesScreen
import com.example.presentation.SharedCoursesViewModel
import com.example.presentation.navigation.Routes
import org.koin.compose.getKoin

@Composable
fun FavoritesFlowNavHost() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Routes.FavouritesMain.route
    ) {
        composable(Routes.FavouritesMain.route) {
            FavouritesScreen(
                onCourseClick = { courseId ->
                    navController.navigate(Routes.CourseDetail.createRoute(courseId))
                }
            )
        }

        composable(Routes.CourseDetail.route) { backStackEntry ->
            val sharedViewModel: SharedCoursesViewModel = getKoin().get()
            val courseId = backStackEntry.arguments
                ?.getString("courseId")
                ?.toIntOrNull()

            val courses by sharedViewModel.courses.collectAsStateWithLifecycle()
            val course = courses.firstOrNull { it.id == courseId }

            course?.let {
                CourseDetailScreen(
                    course = it,
                    sharedViewModel = sharedViewModel,
                    onNavigateBack = { navController.popBackStack() }
                )
            }
        }
    }
}