package com.example.di

import com.example.presentation.FavouritesViewModel
import com.example.presentation.MainViewModel
import com.example.presentation.SharedCoursesViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {

    single {
        SharedCoursesViewModel(
            getCoursesUseCase = get(),
            observeCoursesWithFavoritesUseCase = get(),
            toggleFavoriteUseCase = get()
        )
    }

    viewModel {
        MainViewModel(
            sharedViewModel = get(),
            sortCoursesByDateUseCase = get()
        )
    }

    viewModel {
        FavouritesViewModel(
            coursesFlow = get<SharedCoursesViewModel>().courses,
            getFavoriteCoursesUseCase = get(),
            onToggleFavorite = get<SharedCoursesViewModel>()::toggleFavorite
        )
    }
}