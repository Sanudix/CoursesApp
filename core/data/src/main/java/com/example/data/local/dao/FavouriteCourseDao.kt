package com.example.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.data.local.entity.FavouriteCourseEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface FavouriteCourseDao {

    @Query("SELECT * FROM favorite_courses")
    fun getAllFavorites(): Flow<List<FavouriteCourseEntity>>

    @Query("SELECT * FROM favorite_courses WHERE courseId = :courseId")
    suspend fun getFavoriteById(courseId: Int): FavouriteCourseEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavorite(favorite: FavouriteCourseEntity)

    @Query("DELETE FROM favorite_courses WHERE courseId = :courseId")
    suspend fun deleteFavorite(courseId: Int)

    @Query("SELECT EXISTS(SELECT 1 FROM favorite_courses WHERE courseId = :courseId)")
    suspend fun isFavorite(courseId: Int): Boolean
}