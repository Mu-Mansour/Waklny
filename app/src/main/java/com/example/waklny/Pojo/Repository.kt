package com.example.waklny.Pojo

import com.example.waklny.Retrofit.MealAPI
import com.example.waklny.Room.DaoForMealsDatabase
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Repository @Inject constructor(private val theApCaller:MealAPI , private val theDataBaseDaoForMealsDatabase: DaoForMealsDatabase) {

    fun getTheRandomImage()=theApCaller.getTheRandomMeal()
    fun gteTheMealOfId(MealId:String)=theApCaller.getAMealOf(MealId)
    suspend fun gteTheMealstartsWith(startsWith:Char)=theApCaller.getMeThatMeal(startsWith)
  suspend  fun getSeaFoodForAdapter(type:String)=theApCaller.getSeaFood2(type)
  suspend  fun getAllCategoires()=theApCaller.getAllCategoires()
  suspend  fun getASingleCategoryMeals(theCategory:String)=theApCaller.getAllCategoires(theCategory)
  suspend  fun upSetrAmeal(meal: Meal)=theDataBaseDaoForMealsDatabase.upsertThemean(meal)
  suspend  fun deletethatmeal(meal: Meal)=theDataBaseDaoForMealsDatabase.deleteAmeal(meal)
    fun getTheMealsFromMyDatabase()=theDataBaseDaoForMealsDatabase.getAllMEals()






}