package com.example.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.data.local.dao.FavouriteCourseDao
import com.example.data.local.entity.FavouriteCourseEntity

@Database(
    entities = [FavouriteCourseEntity::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun favoriteCourseDao(): FavouriteCourseDao
}