package com.example.waklny.Room

import androidx.lifecycle.LiveData
import androidx.room.*
import androidx.room.Dao
import com.example.waklny.Pojo.Meal
import kotlinx.coroutines.selects.select


@Dao
interface DaoForMealsDatabase {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsertThemean(meal:Meal)


    @Delete
    suspend fun deleteAmeal(meal: Meal)

    @Query("select * from MealInfor")
    fun getAllMEals():LiveData<List<Meal>>
}