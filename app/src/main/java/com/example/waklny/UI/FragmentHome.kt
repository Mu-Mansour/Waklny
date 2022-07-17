package com.example.waklny.UI

import android.content.res.Configuration
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.sqlite.db.SupportSQLiteOpenHelper
import coil.load
import coil.size.Scale
import coil.transform.RoundedCornersTransformation
import com.example.waklny.Adapters.CategoriesAdapter
import com.example.waklny.Adapters.MostFavouriteAdaptor
import com.example.waklny.Adapters.SearchAdapterRecyclerView
import com.example.waklny.Pojo.MealX
import com.example.waklny.Pojo.Utility
import com.example.waklny.R
import com.example.waklny.ViewModels.GallaryVM
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_home.view.*
import kotlinx.coroutines.launch

@AndroidEntryPoint
class FragmentHome : Fragment() {

    private val theVMOfHomeFragemt :GallaryVM by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (!Utility.isNetworkConnected(requireContext()))
        {
            Toast.makeText(requireContext(), "Check your connection", Toast.LENGTH_SHORT).show()
        }else{
            lifecycleScope.launch {
                theVMOfHomeFragemt.getTheMostFavouriteItems()
                theVMOfHomeFragemt.gettheCategories()
            }
        }


    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val theHomeView = inflater.inflate(R.layout.fragment_home, container, false)

        theVMOfHomeFragemt.theRandomMeal.observe(viewLifecycleOwner) { it1 ->
            it1?.meals?.let { it2 ->
                theHomeView.randomMeal.load(it2[0].strMealThumb) {
                    scale(Scale.FIT)
                    transformations(RoundedCornersTransformation(30f))
                    crossfade(true)
                    crossfade(100)
                    placeholder(R.drawable.hunger)
                }
                theHomeView.MealName.text=it2[0].strMeal

                theHomeView.randomMeal.setOnClickListener {
                    if (Utility.isNetworkConnected(requireContext())) {
                    findNavController().navigate(FragmentHomeDirections.actionFragmentHomeToMealToDisplay(it2[0].idMeal))
                   theVMOfHomeFragemt.theRandomMeal.value=null
                    }else {
                        Toast.makeText(requireContext(), "Check your connection", Toast.LENGTH_SHORT).show()
                    }
                }
                }?: lifecycleScope.launch {
                theVMOfHomeFragemt.getTheRandomMeal()
            }


            }

        theVMOfHomeFragemt.theMostFavoriteMeals.observe(viewLifecycleOwner){thefavorites ->
            thefavorites?.let {
          val theAdapterformostpos=  MostFavouriteAdaptor()
                theAdapterformostpos .getTheMealsList(it as ArrayList<MealX>)
                theHomeView.theFavoriteRV.apply {
                    adapter=theAdapterformostpos
                    layoutManager=LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,false)
                }
            }
        }


        theVMOfHomeFragemt.theCategories.observe(viewLifecycleOwner){theCategories->
            theCategories?.let {
                val theCategoriesAdapter= CategoriesAdapter()
                theCategoriesAdapter.updateTheCategoriesList(it)
                theHomeView.Types.apply {
                    adapter=theCategoriesAdapter
                    layoutManager =
                        if(resources.configuration.orientation== Configuration.ORIENTATION_LANDSCAPE) {
                            GridLayoutManager(requireContext(),4,LinearLayoutManager.VERTICAL,false)

                        } else {
                            GridLayoutManager(requireContext(),3,LinearLayoutManager.VERTICAL,false)

                        }

                }

            }

        }
        val theSearchedAdapter=SearchAdapterRecyclerView()
        theVMOfHomeFragemt. theMsearchResults.observe(viewLifecycleOwner){
            it?.meals?.let {themeals->

                    theSearchedAdapter.updateTheMealsFoundFromSearch(it)
                    theHomeView.itemsFromSearch.apply {
                        adapter = theSearchedAdapter
                        layoutManager = LinearLayoutManager(
                            requireContext(),
                            LinearLayoutManager.VERTICAL,
                            false
                        )
                    }
            }?:Toast.makeText(requireContext(), "0 item's found", Toast.LENGTH_SHORT).show()


        }



        theHomeView.theHome.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                theSearchedAdapter.clearTheItems()
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun afterTextChanged(p0: Editable?) {
                lifecycleScope.launch {
                    if (p0.toString()!= "")
                    {
                        theVMOfHomeFragemt.getMeAmealStartsWith(p0.toString().toCharArray()[0])
                    }
                    else if (p0.toString()=="")
                    {
                        theSearchedAdapter.clearTheItems()
                    }

                }
            }

        })


            return theHomeView
        }



}