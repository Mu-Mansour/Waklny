package com.example.waklny.DI

import android.app.Application
import androidx.room.Dao
import androidx.room.Room
import com.example.waklny.Retrofit.MealAPI
import com.example.waklny.Room.DaoForMealsDatabase
import com.example.waklny.Room.MealsDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {


    @Provides
    @Singleton
    fun provideTheRetrofit(): Retrofit =  Retrofit.Builder().baseUrl("https://www.themealdb.com/api/json/v1/1/").addConverterFactory(GsonConverterFactory.create()).build()

    @Provides
    @Singleton
    fun provideTheApiInterface(retrofit:Retrofit):MealAPI=retrofit.create(MealAPI::class.java)

    @Provides
    @Singleton
    fun provideTheDataBase(app:Application):MealsDataBase{
        return  Room.databaseBuilder(app,MealsDataBase::class.java,"TheDatabase")
            .fallbackToDestructiveMigration()
            //.allowMainThreadQueries()
            .build()
    }



    @Provides
    @Singleton
    fun provideTheDao(db:MealsDataBase):DaoForMealsDatabase=db.theDao()
}