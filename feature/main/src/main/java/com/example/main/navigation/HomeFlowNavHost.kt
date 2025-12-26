package com.example.main.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.course_detail.CourseDetailScreen
import com.example.main.MainScreen
import com.example.presentation.SharedCoursesViewModel
import org.koin.compose.getKoin

@Composable
fun HomeFlowNavHost() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = "homeMain"
    ) {
        composable("homeMain") {
            MainScreen(
                onCourseClick = { courseId ->
                    navController.navigate("course/$courseId")
                }
            )
        }

        composable("course/{courseId}") { backStackEntry ->
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