package com.example.waklny.UI

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.example.waklny.Adapters.TheVavouritesAdapter
import com.example.waklny.R
import com.example.waklny.ViewModels.TheFavouriteMealsViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_favourites.view.*
import kotlinx.coroutines.launch


@AndroidEntryPoint
class Favourites : Fragment() {

    private val theViewModelOfFavourites:TheFavouriteMealsViewModel by viewModels ()



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val theFavouriteView =inflater.inflate(R.layout.fragment_favourites, container, false)
        val theRVdapter = TheVavouritesAdapter(parentFragmentManager)
        lifecycleScope.launch {
            theViewModelOfFavourites.allTheMeals.observe(viewLifecycleOwner){
                theFavouriteView.favouriteRV.apply {
                    theRVdapter.theDiffer.submitList(it)
                    adapter=theRVdapter
                    layoutManager=GridLayoutManager(requireContext(),4,GridLayoutManager.VERTICAL,false)

                }

            }
        }



        return theFavouriteView
    }


}