package com.example.waklny.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.size.Scale
import coil.transform.CircleCropTransformation
import com.example.waklny.Pojo.MealXX
import com.example.waklny.Pojo.Utility
import com.example.waklny.R
import com.example.waklny.UI.SingleCategoryMealsDirections
import kotlinx.android.synthetic.main.singe_meal_for_single_category.view.*

class SingleCategoryFragmentAdapter: RecyclerView.Adapter<SingleCategoryFragmentAdapter.SingleCategoryFragmentAdapterViewHolder>() {
    private var theMealsItems= ArrayList<MealXX> ()

    inner class SingleCategoryFragmentAdapterViewHolder(theView:View):RecyclerView.ViewHolder(theView) {
        val theimageVieForMeal= theView.imageViewforAsingleMeal
        val thetextVieForMeal= theView.theMealNameForAsingleMeal
        init {
            theimageVieForMeal.setOnClickListener {
                if (adapterPosition!= RecyclerView.NO_POSITION) {
                    if (Utility.isNetworkConnected(theView.context)) {
                        theView.findNavController().navigate(
                            SingleCategoryMealsDirections.actionSingleCategoryMealsToMealToDisplay(
                                theMealsItems[adapterPosition].idMeal
                            )
                        )
                    }else {
                        Toast.makeText(theView.context, "Check your connection", Toast.LENGTH_SHORT).show()
                    }
                }
            }

        }

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SingleCategoryFragmentAdapterViewHolder {

        return SingleCategoryFragmentAdapterViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.singe_meal_for_single_category, parent, false))
    }

    override fun onBindViewHolder(holder: SingleCategoryFragmentAdapterViewHolder, position: Int) {
        holder.theimageVieForMeal.load(theMealsItems[position].strMealThumb){
            scale(Scale.FIT)
            transformations(CircleCropTransformation())
            crossfade(true)
            crossfade(500)
        }
       holder. thetextVieForMeal.text=theMealsItems[position].strMeal

    }

    override fun getItemCount(): Int {
        return theMealsItems.size
    }

    fun updateTheMealsCat(mealsList:List<MealXX>)
    {
        theMealsItems= mealsList as ArrayList<MealXX>
        notifyDataSetChanged()
    }
}