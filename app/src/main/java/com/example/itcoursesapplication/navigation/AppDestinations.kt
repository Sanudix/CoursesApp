package com.example.itcoursesapplication.navigation

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.example.itcoursesapplication.R

enum class AppDestinations(
    @StringRes val labelResId: Int,
    @DrawableRes val iconResId: Int,
    @DrawableRes val selectedIconResId: Int
) {
    HOME(
        R.string.app_main_label,
        R.drawable.ic_home,
        R.drawable.ic_home
    ),
    FAVORITES(
        R.string.app_favourite_label,
        R.drawable.ic_favourites_navigation,
        R.drawable.ic_favourites_navigation
    ),
    PROFILE(
        R.string.app_account_label,
        R.drawable.ic_account,
        R.drawable.ic_account
    ),
}