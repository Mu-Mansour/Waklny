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
import com.example.waklny.Pojo.Category
import com.example.waklny.Pojo.Utility
import com.example.waklny.R
import com.example.waklny.UI.FragmentHomeDirections
import kotlinx.android.synthetic.main.category_item_for_catrv.view.*

class CategoriesAdapter : RecyclerView.Adapter<CategoriesAdapter.CategoriesViewHoler>() {

  private  var theCategoriesList =ArrayList<Category> ()

    inner class CategoriesViewHoler(theVie:View):RecyclerView.ViewHolder(theVie){

        val theCateItemimage=theVie.imageViewforCategoryRV
        val theCateItemTV=theVie.textView2forAdapterCategories
        init {
            theCateItemimage.setOnClickListener {
                if (adapterPosition!= RecyclerView.NO_POSITION)
                {
                    if (Utility.isNetworkConnected(theVie.context))
                    {theVie.findNavController().navigate(FragmentHomeDirections.actionFragmentHomeToSingleCategoryMeals(theCategoriesList[adapterPosition].strCategory))
                }else {
                        Toast.makeText(theVie.context, "Check your connection", Toast.LENGTH_SHORT).show()
                    }
                }
            }

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoriesViewHoler {
        return CategoriesViewHoler(LayoutInflater.from(parent.context).inflate(R.layout.category_item_for_catrv, parent, false))
    }

    override fun onBindViewHolder(holder: CategoriesViewHoler, position: Int) {
        holder. theCateItemimage.load(theCategoriesList[position].strCategoryThumb){
            scale(Scale.FIT)
            transformations(RoundedCornersTransformation(30f))
            crossfade(true)
            crossfade(100)

        }
        holder.theCateItemTV.text=theCategoriesList[position].strCategory
    }

    override fun getItemCount(): Int {
    return    theCategoriesList.size
    }


    fun updateTheCategoriesList(cats:List<Category>)
    {
        theCategoriesList=cats as ArrayList<Category>
        notifyDataSetChanged()
    }
}