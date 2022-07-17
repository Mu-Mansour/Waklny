package com.example.waklny.Retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

object InstanceOfRetrofit {
    val theApi:MealAPI by lazy {
        Retrofit.Builder().baseUrl("https://www.themealdb.com/api/json/v1/1/").addConverterFactory(GsonConverterFactory.create()).build().create(MealAPI::class.java)
    }
}