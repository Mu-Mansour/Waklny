package com.example.waklny.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.size.Scale
import coil.transform.RoundedCornersTransformation
import com.example.waklny.Pojo.MealX
import com.example.waklny.Pojo.Utility
import com.example.waklny.R
import com.example.waklny.UI.FragmentHomeDirections
import com.google.gson.internal.bind.ReflectiveTypeAdapterFactory
import kotlinx.android.synthetic.main.most_favourite_item.view.*

class MostFavouriteAdaptor : RecyclerView.Adapter<MostFavouriteAdaptor.TheMostFavoriteAdapter>() {

    private var theMealsList:ArrayList<MealX> = ArrayList()
    inner class TheMostFavoriteAdapter(theView:View):RecyclerView.ViewHolder(theView)
    {
     val themageInsideAdapter=theView.mostfavoriteItem
        init {
            themageInsideAdapter.setOnClickListener {
                if (adapterPosition !=RecyclerView.NO_POSITION)
                {
                    if (Utility.isNetworkConnected(theView.context)){
                    theView.findNavController().navigate(FragmentHomeDirections.actionFragmentHomeToMealToDisplay(theMealsList[adapterPosition].idMeal))
                }else {
                        Toast.makeText(theView.context, "Check your connection", Toast.LENGTH_SHORT).show()
                    }
                }
            }


        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TheMostFavoriteAdapter {
        return TheMostFavoriteAdapter( LayoutInflater.from(parent.context).inflate(R.layout.most_favourite_item, parent, false))
    }

    override fun onBindViewHolder(holder: TheMostFavoriteAdapter, position: Int) {
        holder.themageInsideAdapter.load(theMealsList[position].strMealThumb){
            scale(Scale.FIT)
            transformations(RoundedCornersTransformation(30f))
            crossfade(true)
            crossfade(100)

        }
    }

    override fun getItemCount(): Int {
        return theMealsList.size
    }

    fun getTheMealsList(theMealsListToAdapted:ArrayList<MealX>){
        theMealsList=theMealsListToAdapted
        notifyDataSetChanged()
    }
}