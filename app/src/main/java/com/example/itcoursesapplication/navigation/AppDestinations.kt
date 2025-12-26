package com.example.itcoursesapplication.navigation

import com.example.itcoursesapplication.R

enum class AppDestinations(
    val label: String,
    val iconResId: Int,
    val selectedIconResId: Int
) {
    HOME("Главная", R.drawable.ic_home, R.drawable.ic_home),
    FAVORITES(
        "Избранное",
        R.drawable.ic_favourites_navigation,
        R.drawable.ic_favourites_navigation
    ),
    PROFILE("Аккаунт", R.drawable.ic_account, R.drawable.ic_account),
}