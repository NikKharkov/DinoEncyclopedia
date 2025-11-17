package com.example.dinoencyclopedia.data.preferences

import android.content.Context
import androidx.core.content.edit

class UserPreferences(private val context: Context) {
    private val preferences =
        context.getSharedPreferences(KEY_USER_PREFERENCES, Context.MODE_PRIVATE)

    var isOnboardingShown: Boolean
        get() = preferences.getBoolean(KEY_ONBOARDING_SHOWN, false)
        set(value) = preferences.edit { putBoolean(KEY_ONBOARDING_SHOWN, value) }

    var dinosaurOfTheDayId: Int
        get() = preferences.getInt(KEY_DINO_DAY_ID, -1)
        set(value) = preferences.edit { putInt(KEY_DINO_DAY_ID, value) }

    companion object {
        private const val KEY_USER_PREFERENCES = "user_preferences"
        private const val KEY_ONBOARDING_SHOWN = "onboarding_shown"
        private const val KEY_DINO_DAY_ID = "dinosaur_of_the_day_id"
    }
}