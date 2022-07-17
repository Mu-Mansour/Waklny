package com.example.waklny.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.size.Scale
import coil.transform.CircleCropTransformation
import com.example.waklny.Pojo.Meal
import com.example.waklny.R
import com.example.waklny.UI.FavouriteDeletetionDialouge
import com.example.waklny.UI.FavouritesDialougeFragemnt
import kotlinx.android.synthetic.main.category_item_for_catrv.view.*
import kotlinx.android.synthetic.main.singe_meal_for_single_category.view.*

class TheVavouritesAdapter(private val thenamager: FragmentManager):RecyclerView.Adapter<TheVavouritesAdapter.TheFavouritesViewHolde>() {


     val theDifful= object :DiffUtil.ItemCallback<Meal>(){
        override fun areItemsTheSame(oldItem: Meal, newItem: Meal): Boolean {
            return oldItem.idMeal==newItem.idMeal
        }

        override fun areContentsTheSame(oldItem: Meal, newItem: Meal): Boolean {
            return oldItem==newItem
        }

    }
    val theDiffer=AsyncListDiffer(this,theDifful)

    inner class TheFavouritesViewHolde(theFavoriteRVviewholder:View):RecyclerView.ViewHolder(theFavoriteRVviewholder){
        val theImageForFavMeal = theFavoriteRVviewholder.imageViewforAsingleMeal
        val thename = theFavoriteRVviewholder.theMealNameForAsingleMeal


        init {

            thename.setOnClickListener {
                if (adapterPosition!=RecyclerView.NO_POSITION)
                {
                    Toast.makeText(it.context, "${theDiffer.currentList[adapterPosition].strIngredient1}", Toast.LENGTH_SHORT).show()

                }
            }

                theImageForFavMeal.setOnClickListener {
                   if (adapterPosition!=RecyclerView.NO_POSITION)
                    {
                    val thePopUp=   FavouritesDialougeFragemnt(theDiffer.currentList[adapterPosition])
                    thePopUp.show(thenamager,"")

                }


            }


            theImageForFavMeal.setOnLongClickListener {
                if (adapterPosition!=RecyclerView.NO_POSITION)
                    {
                        val thePopUpfordelete= FavouriteDeletetionDialouge(theDiffer.currentList[adapterPosition])
                        thePopUpfordelete.show(thenamager,"")
                        return@setOnLongClickListener true
                    }
                else return@setOnLongClickListener false
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TheFavouritesViewHolde {

        return TheFavouritesViewHolde(LayoutInflater.from(parent.context).inflate(R.layout.singe_meal_for_single_category, parent, false))
    }

    override fun onBindViewHolder(holder: TheFavouritesViewHolde, position: Int) {
        holder.theImageForFavMeal.load(theDiffer.currentList[position].strMealThumb){
            scale(Scale.FIT)
            transformations(CircleCropTransformation())
            crossfade(true)
            crossfade(500)
        }
        holder.thename.text=theDiffer.currentList[position].strMeal
    }

    override fun getItemCount(): Int =theDiffer.currentList.size
}