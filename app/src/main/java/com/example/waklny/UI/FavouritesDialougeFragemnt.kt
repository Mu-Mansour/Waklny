package com.example.waklny.UI

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.waklny.Pojo.Meal
import com.example.waklny.R
import kotlinx.android.synthetic.main.dialouge_fragemnt_popup.view.*

class FavouritesDialougeFragemnt(private val meal: Meal):DialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val thePopupView= inflater.inflate(R.layout.dialouge_fragemnt_popup, container, false)
        thePopupView.comesFromdialouge.text=meal.strArea
        thePopupView.typefromdialouge.text=meal.strCategory
        thePopupView.dismissdialouge.text=meal.strInstructions
        thePopupView.button.setOnClickListener {
            dismiss()
        }

        return thePopupView

    }


}