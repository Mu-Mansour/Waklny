package com.example.waklny.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.size.Scale
import coil.transform.CircleCropTransformation
import coil.transform.RoundedCornersTransformation
import com.example.waklny.Pojo.MealList
import com.example.waklny.Pojo.MealX
import com.example.waklny.Pojo.Utility
import com.example.waklny.R
import com.example.waklny.UI.FragmentHomeDirections
import kotlinx.android.synthetic.main.searc_item_for_searchrv.view.*

class SearchAdapterRecyclerView: RecyclerView.Adapter<SearchAdapterRecyclerView.SearchedItemsViewHolder>() {
    private var theItemsGot:MealList? = null


    inner class SearchedItemsViewHolder(searchView:View):RecyclerView.ViewHolder(searchView){
        val theSearchedImage =searchView.imageViewforsearchedmeal
        val theSearchedName =searchView.mealnameForSearchrv
        init {
            theSearchedName.setOnClickListener {
                if (adapterPosition!= RecyclerView.NO_POSITION)
                {
                    theItemsGot?.let {
                        if (Utility.isNetworkConnected(searchView.context)){
                        searchView.findNavController().navigate(FragmentHomeDirections.actionFragmentHomeToMealToDisplay(it.meals[adapterPosition].idMeal))

                    }else {
                            Toast.makeText(searchView.context, "Check your connection", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchedItemsViewHolder {
        return   SearchedItemsViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.searc_item_for_searchrv, parent, false))
    }

    override fun onBindViewHolder(holder: SearchedItemsViewHolder, position: Int) {

        theItemsGot?.let {
            holder.theSearchedImage.load(it.meals[position].strMealThumb){
                scale(Scale.FIT)
                transformations(CircleCropTransformation())
                crossfade(true)
                crossfade(100)

            }
            holder.theSearchedName.text=it.meals[position].strMeal
        }


    }

    override fun getItemCount(): Int {
        theItemsGot?.let {
            return it.meals.size
        }?:return 0
    }
    fun updateTheMealsFoundFromSearch(meals:MealList){
        theItemsGot= null
       theItemsGot = meals
        notifyDataSetChanged()


    }
    fun clearTheItems(){
        theItemsGot= null
        notifyDataSetChanged()
    }
}