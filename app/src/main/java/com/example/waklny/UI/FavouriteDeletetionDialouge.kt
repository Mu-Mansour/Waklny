package com.example.waklny.UI

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.waklny.Pojo.Meal
import com.example.waklny.R
import com.example.waklny.ViewModels.TheFavouriteMealsViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.delete_this_meal_popup.view.*
import kotlinx.coroutines.launch

@AndroidEntryPoint
class FavouriteDeletetionDialouge(private val meal: Meal): DialogFragment() {

    private val theviwForFavourite: TheFavouriteMealsViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


      val  thePopupViewFordeletion= inflater.inflate(R.layout.delete_this_meal_popup, container, false)

        thePopupViewFordeletion.sure.setOnClickListener {
            lifecycleScope.launch {
                theviwForFavourite.deletethismean(meal).await()
                dismiss()
                Toast.makeText(it.context, "Deleted", Toast.LENGTH_SHORT).show()

            }


        }
        thePopupViewFordeletion.Dismiss.setOnClickListener {
            dismiss()
            Toast.makeText(it.context, "Canceled", Toast.LENGTH_SHORT).show()

        }
        return thePopupViewFordeletion
    }

}