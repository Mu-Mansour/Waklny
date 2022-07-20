package com.example.waklny.UI

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.waklny.Adapters.SingleCategoryFragmentAdapter
import com.example.waklny.Pojo.Utility
import com.example.waklny.R
import com.example.waklny.ViewModels.SingleCategoryMealsVM
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_single_category_meals.view.*
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SingleCategoryMeals : Fragment() {
    private val theMealToDisplayArgs by navArgs<SingleCategoryMealsArgs>()
    private val theSingleCateVm :SingleCategoryMealsVM by viewModels ()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lifecycleScope.launch {
            theSingleCateVm.getTheMealsOf(theMealToDisplayArgs.categoryOfmeals)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val theSingleCategoryMealView = inflater.inflate(R.layout.fragment_single_category_meals, container, false)

        theSingleCategoryMealView.ToGoBackFromsingleToHome.setOnClickListener {
            if (Utility.isNetworkConnected(requireContext())) {
                findNavController().navigate(SingleCategoryMealsDirections.actionSingleCategoryMealsToFragmentHome())
            } else {
                Toast.makeText(requireContext(), "Check your connection", Toast.LENGTH_SHORT).show()
            }

        }
        theSingleCateVm.theCategories.observe(viewLifecycleOwner) {
            theSingleCategoryMealView.TotalCaountTVForSingleCategory.text="Total count of meals Is :${it.size}"

            val thisAdapterForThisMeals = SingleCategoryFragmentAdapter()
            thisAdapterForThisMeals.updateTheMealsCat(it)
            theSingleCategoryMealView.  RVForAllFromSingleCategory.apply {
                adapter =thisAdapterForThisMeals
                layoutManager=GridLayoutManager(requireContext(),4,LinearLayoutManager.VERTICAL,false)
            }


        }
        return theSingleCategoryMealView
    }


    }
