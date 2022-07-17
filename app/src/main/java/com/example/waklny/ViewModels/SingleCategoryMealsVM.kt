package com.example.waklny.ViewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.waklny.Pojo.MealXX
import com.example.waklny.Pojo.Repository
import com.example.waklny.Pojo.TheSingleCategory
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SingleCategoryMealsVM @Inject constructor (private val theRepo:Repository):ViewModel() {

    val theCategories = MutableLiveData<List<MealXX>> ()

    fun getTheMealsOf(category:String)=viewModelScope.launch {
        if (theRepo.getASingleCategoryMeals(category).isSuccessful)
        {
            theRepo.getASingleCategoryMeals(category).body()?.let {
                theCategories.value=it.meals
            }

        }
        else theCategories.value=null

    }
}