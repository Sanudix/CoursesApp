package com.example.di

import androidx.room.Room
import com.example.data.local.database.AppDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val databaseModule = module {
    single {
        Room.databaseBuilder(
            androidContext(),
            AppDatabase::class.java,
            "courses_database"
        ).build()
    }

    single { get<AppDatabase>().favoriteCourseDao() }
}