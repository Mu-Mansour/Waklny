package com.example.waklny.ViewModels

import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.waklny.Pojo.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class GallaryVM @Inject constructor(private val repo: Repository):ViewModel() {

    val theRandomMeal = MutableLiveData<MealList> ()
    val theMostFavoriteMeals = MutableLiveData<List<MealX>> ()
    val theMsearchResults = MutableLiveData<MealList> ()
    val theCategories = MutableLiveData<List<Category>> ()
init {
    getTheRandomMeal()

}
    fun getMeAmealStartsWith(letter:Char) = viewModelScope.launch(Dispatchers.IO) {
        if (repo.gteTheMealstartsWith(letter).isSuccessful)
        {
            viewModelScope.launch(Dispatchers.Main){
            theMsearchResults.value=repo.gteTheMealstartsWith(letter).body()

        }
        }

        else{
            viewModelScope.launch(Dispatchers.Main){
            theMsearchResults.value= null
        }
        }

    }

    fun getTheRandomMeal()=
        viewModelScope.launch(Dispatchers.IO) {

            repo.getTheRandomImage().enqueue(object :Callback<MealList>{
                override fun onResponse(call: Call<MealList>, response: Response<MealList>) {
                    if (!response.isSuccessful)
                    {

                        viewModelScope.launch(Dispatchers.Main){
                            theRandomMeal.value=null
                        }

                    }
                    else
                    {
                        viewModelScope.launch(Dispatchers.Main){
                            theRandomMeal.value=response.body()
                        }

                    }
                }

                override fun onFailure(call: Call<MealList>, t: Throwable) {
                }

            })
        }
    fun getTheMostFavouriteItems()= viewModelScope.launch {

        if (repo.getSeaFoodForAdapter("Seafood").isSuccessful) {
            viewModelScope.launch(Dispatchers.Main) {
                theMostFavoriteMeals.value = repo.getSeaFoodForAdapter("Seafood").body()!!.meals
            }


        } else theMostFavoriteMeals.value = null

    }


    fun gettheCategories()=viewModelScope.launch {
        if (repo.getAllCategoires().isSuccessful)
        {
            repo.getAllCategoires().body().let { themealsCats->
                themealsCats?.let {
                    theCategories.value=it.categories
                }

            }
        }
        else theCategories.value=null

    }
}