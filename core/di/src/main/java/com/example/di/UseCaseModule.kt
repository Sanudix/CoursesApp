package com.example.di

import com.example.domain.usecase.*
import org.koin.dsl.module

val useCaseModule = module {
    factory { GetFavouriteCourseIdsUseCase(repository = get()) }
    factory { GetCoursesUseCase(repository = get()) }
    factory { ToggleFavouriteUseCase(repository = get()) }
    factory { GetFavouriteCoursesUseCase() }
    factory { SortCoursesByDateUseCase() }
    factory { ObserveCoursesWithFavoritesUseCase(getFavoriteCourseIdsUseCase = get()) }
}