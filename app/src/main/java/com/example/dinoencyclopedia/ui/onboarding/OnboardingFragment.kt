package com.example.dinoencyclopedia.ui.onboarding

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.dinoencyclopedia.R
import com.example.dinoencyclopedia.data.preferences.UserPreferences
import com.example.dinoencyclopedia.databinding.FragmentOnboardingBinding
import com.example.dinoencyclopedia.domain.onboarding.OnboardingPage
import com.example.dinoencyclopedia.ui.onboarding.OnboardingAdapter
import org.koin.android.ext.android.inject

class OnboardingFragment : Fragment() {

    private var _binding: FragmentOnboardingBinding? = null
    private val binding get() = _binding!!

    private val userPreferences: UserPreferences by inject()

    private val onboardingPages = listOf(
        OnboardingPage(
            R.raw.dino_greeting,
            R.string.onboarding_label1,
            R.string.onboarding_description1
        ),
        OnboardingPage(
            R.raw.dino_music,
            R.string.onboarding_label2,
            R.string.onboarding_description2
        ),
        OnboardingPage(
            R.raw.map,
            R.string.onboarding_label3,
            R.string.onboarding_description3
        )
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentOnboardingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = OnboardingAdapter(onboardingPages)
        binding.viewPager.adapter = adapter

        binding.dotsIndicator.attachTo(binding.viewPager)

        binding.btnNext.setOnClickListener {
            if (binding.viewPager.currentItem < onboardingPages.size - 1) {
                binding.viewPager.currentItem++
            } else {
                navigateToHome()
            }
        }

        binding.btnSkip.setOnClickListener {
            navigateToHome()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun navigateToHome() {
        userPreferences.isOnboardingShown = true
        findNavController().navigate(R.id.onboarding_to_home)
    }
}