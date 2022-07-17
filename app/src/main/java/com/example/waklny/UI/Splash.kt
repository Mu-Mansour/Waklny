package com.example.waklny.UI

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.waklny.Pojo.Utility
import com.example.waklny.R
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


@AndroidEntryPoint
class Splash : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        lifecycleScope.launch {
            delay(1500)
            if (Utility.isNetworkConnected(requireContext())){
                findNavController().navigate(SplashDirections.actionSplashToFragmentHome())
            }
            else{
                Toast.makeText(requireContext(), "No internet , app is shutting down ", Toast.LENGTH_SHORT).show()
                delay(1000)
                requireActivity().finish()
            }

        }
        return inflater.inflate(R.layout.fragment_splash, container, false)
    }





}