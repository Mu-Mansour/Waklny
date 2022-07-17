package com.example.waklny.Room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.waklny.Pojo.Meal
import com.example.waklny.Pojo.MealTypeConvertor


@Database(entities = [Meal::class], version = 1)
@TypeConverters(MealTypeConvertor::class)
abstract class MealsDataBase():RoomDatabase() {
    abstract fun theDao ():DaoForMealsDatabase
}