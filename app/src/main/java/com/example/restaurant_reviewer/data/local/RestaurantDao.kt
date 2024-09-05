package com.example.restaurant_reviewer.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.restaurant_reviewer.data.local.RestaurantEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface RestaurantDao {
    @Query("SELECT * FROM restaurant")
    fun getFavoriteResto(): Flow<List<RestaurantEntity>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun saveFavoriteResto(resto: RestaurantEntity)

    @Query("DELETE FROM restaurant WHERE id = :id")
    suspend fun deleteResto(id: String)
}