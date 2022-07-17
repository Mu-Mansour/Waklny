package com.example.waklny.UI

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import coil.load
import coil.size.Scale
import coil.transform.RoundedCornersTransformation
import com.example.waklny.Pojo.Utility
import com.example.waklny.R
import com.example.waklny.ViewModels.MealToDisplayViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_meal_to_display.view.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
@AndroidEntryPoint
class MealToDisplay : Fragment() {

     private val theMealToDisplayArgs by navArgs<MealToDisplayArgs>()
     private val mealToDisplayViewModel: MealToDisplayViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        theMealToDisplayArgs.mealId.let {
            lifecycleScope.launch(Dispatchers.Main) {
                mealToDisplayViewModel.getTheSpecificMeal(it)
            }

        }

    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val theViewFromMealToDisplay =inflater.inflate(R.layout.fragment_meal_to_display, container, false)


        mealToDisplayViewModel.theSpecficMeal.observe(viewLifecycleOwner) { theMealList ->
            theMealList.meals[0].let {themeagGotten ->
                theViewFromMealToDisplay.progressBar.visibility =View.GONE
                theViewFromMealToDisplay.theImageFromThemealsToViw.load(themeagGotten.strMealThumb){
                    scale(Scale.FIT)
                    transformations(RoundedCornersTransformation(10f))
                    crossfade(true)
                    crossfade(100)
                }



                theViewFromMealToDisplay.theColapsentToolBard.apply {
                    title=themeagGotten.strMeal
                }



                theViewFromMealToDisplay.cate.text="Category : ${themeagGotten.strCategory}"
                theViewFromMealToDisplay.comesFrom.text="Cuisine : ${themeagGotten.strArea}"
                theViewFromMealToDisplay.TheMealDetails.text= themeagGotten.strInstructions
                theViewFromMealToDisplay.imageView2.setOnClickListener {
                 requireActivity().startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(themeagGotten.strYoutube)))

                }

                theViewFromMealToDisplay.ToSave.setOnClickListener{
                    mealToDisplayViewModel.upsertthismeal(theMealList.meals[0]).isCompleted.let {
                        Toast.makeText(requireContext(), "Saved", Toast.LENGTH_SHORT).show()
                    }
                }


            }


        }



        theViewFromMealToDisplay. imageView3.setOnClickListener {
            if (Utility.isNetworkConnected(requireContext())) {
                findNavController().navigate(MealToDisplayDirections.actionMealToDisplayToFragmentHome())
            }else {
                Toast.makeText(requireContext(), "Check your connection", Toast.LENGTH_SHORT).show()
            }
        }


        return theViewFromMealToDisplay
    }






}