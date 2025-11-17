package com.example.dinoencyclopedia.domain.onboarding

import androidx.annotation.RawRes
import androidx.annotation.StringRes

data class OnboardingPage(
    @RawRes val animationId: Int,
    @StringRes val labelId: Int,
    @StringRes val descriptionId: Int
)
