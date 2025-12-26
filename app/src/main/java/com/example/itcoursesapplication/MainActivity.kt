package com.example.itcoursesapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.itcoursesapplication.navigation.AppNavigation
import com.example.itcoursesapplication.ui.theme.ITCoursesApplicationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ITCoursesApplicationTheme {
                AppNavigation()
            }
        }
    }
}