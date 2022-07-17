package com.example.waklny.ViewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.waklny.Pojo.Meal
import com.example.waklny.Pojo.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class TheFavouriteMealsViewModel @Inject constructor(private val therepoForFavourites:Repository):ViewModel()
{
    var allTheMeals = therepoForFavourites.getTheMealsFromMyDatabase()

    fun deletethismean(meal: Meal)= viewModelScope.async {
        therepoForFavourites.deletethatmeal(meal)
    }



}