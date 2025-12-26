package com.example.itcoursesapplication.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.course_detail.CourseDetailScreen
import com.example.favourites.FavouritesScreen
import com.example.login.LoginScreen
import com.example.presentation.SharedCoursesViewModel
import org.koin.compose.getKoin

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    val sharedViewModel: SharedCoursesViewModel = getKoin().get()

    NavHost(
        navController = navController,
        startDestination = "login"
    ) {
        composable("login") {
            LoginScreen(
                onLoginSuccess = {
                    navController.navigate("main") {
                        popUpTo(0) { inclusive = true }
                    }
                }
            )
        }

        composable("main") {
            MainFlowNavHost()
        }

        composable("favourites") {
            FavouritesScreen(
                onCourseClick = {}
            )
        }

        composable("details/{id}") { backStackEntry ->
            val courseId = backStackEntry.arguments?.getString("id")!!.toInt()
            val course = sharedViewModel.courses
                .collectAsStateWithLifecycle()
                .value
                .first { it.id == courseId }

            CourseDetailScreen(
                course = course,
                sharedViewModel = sharedViewModel
            )
        }
    }
}