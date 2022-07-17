package com.example.waklny.Retrofit

import com.example.waklny.Pojo.*
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MealAPI {
    @GET("random.php")
    fun getTheRandomMeal(): Call<MealList>

    @GET("lookup.php")
     fun getAMealOf(@Query("i") id:String):Call<MealList>


    @GET("filter.php")
   suspend  fun getSeaFood2(@Query("c") mealsType:String):Response<SeaFood>

    @GET("categories.php")
   suspend fun getAllCategoires():Response<MealsCategories>

   @GET("search.php")
   suspend fun getMeThatMeal(@Query("f") id:Char):Response<MealList>

   @GET("filter.php?")
   suspend fun getAllCategoires(@Query("c")theSingleCategory:String):Response<TheSingleCategory>

}