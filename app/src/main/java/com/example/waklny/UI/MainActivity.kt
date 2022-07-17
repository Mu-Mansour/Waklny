package com.example.waklny.UI

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.waklny.R
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        TheBtnNav.setupWithNavController(fragmentContainerView2.findNavController())




        fragmentContainerView2.findNavController().addOnDestinationChangedListener { _, destination, _ ->

            when (destination.id) {
                R.id.mealToDisplay, R.id.singleCategoryMeals, R.id.splash -> {
                    TheBtnNav.visibility = View.GONE
                }
                else -> TheBtnNav.visibility = View.VISIBLE
            }


        }
    }
}