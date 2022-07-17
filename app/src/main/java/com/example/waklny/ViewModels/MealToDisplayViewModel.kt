package com.example.waklny.ViewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.waklny.Pojo.Meal
import com.example.waklny.Pojo.MealList
import com.example.waklny.Pojo.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject
@HiltViewModel
class MealToDisplayViewModel @Inject constructor (private val theRepository: Repository) : ViewModel() {

    val theSpecficMeal= MutableLiveData <MealList>()

    fun getTheSpecificMeal(theID:String){

        viewModelScope.launch {
            theRepository.gteTheMealOfId(theID).clone().enqueue(object : Callback<MealList>{
                override fun onResponse(call: Call<MealList>, response: Response<MealList>) {

                    if (!response.isSuccessful)
                    {

                        viewModelScope.launch(Dispatchers.Main){
                            theSpecficMeal.value=null
                        }

                    }
                    else
                    {
                        viewModelScope.launch(Dispatchers.Main){
                            theSpecficMeal.value=response.body()
                        }

                    }
                }

                override fun onFailure(call: Call<MealList>, t: Throwable) {
                }

            })
        }



    }

    fun upsertthismeal(meal: Meal)= viewModelScope.async {
        theRepository.upSetrAmeal(meal)
    }


}