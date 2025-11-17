package com.example.dinoencyclopedia.ui.onboarding

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.dinoencyclopedia.databinding.ItemOnboardingPageBinding
import com.example.dinoencyclopedia.domain.onboarding.OnboardingPage

class OnboardingAdapter(
    private val pages: List<OnboardingPage>
) : RecyclerView.Adapter<OnboardingAdapter.OnboardingViewHolder>() {

    inner class OnboardingViewHolder(private val binding: ItemOnboardingPageBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: OnboardingPage) {
            binding.onboardingLottie.setAnimation(item.animationId)
            binding.onboardingLabel.setText(item.labelId)
            binding.onboardingDescription.setText(item.descriptionId)
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): OnboardingViewHolder {
        val binding = ItemOnboardingPageBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return OnboardingViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: OnboardingViewHolder,
        position: Int
    ) {
        holder.bind(pages[position])
    }

    override fun getItemCount(): Int = pages.size
}