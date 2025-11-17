package com.example.dinoencyclopedia.ui.splash

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.dinoencyclopedia.R
import com.example.dinoencyclopedia.data.preferences.UserPreferences
import com.example.dinoencyclopedia.databinding.FragmentSplashBinding
import org.koin.android.ext.android.inject

class SplashFragment : Fragment() {

    private var _binding: FragmentSplashBinding? = null
    private val binding get() = _binding!!

    private val userPreferences: UserPreferences by inject()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSplashBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val destination = if (userPreferences.isOnboardingShown) {
            R.id.splash_to_home
        } else {
            R.id.splash_to_onboarding
        }

        view.postDelayed({
            findNavController().navigate(destination)
        },2000L)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}