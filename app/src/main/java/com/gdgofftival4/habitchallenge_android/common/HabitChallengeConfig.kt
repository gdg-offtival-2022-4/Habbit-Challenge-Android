package com.gdgofftival4.habitchallenge_android.common

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import com.gdgofftival4.habitchallenge_android.HabitChallengeApplication

object HabitChallengeConfig {
    private const val PREFERENCE = "HabitChallenge.preference"
    private const val USER_ID = "userId"

    private val preference: SharedPreferences by lazy {
        HabitChallengeApplication.getApplication()
            .getSharedPreferences(PREFERENCE, Context.MODE_PRIVATE)
    }

    var userId: String?
        get() = preference.getString(USER_ID, null)
        set(value) = preference.edit { putString(USER_ID, value) }
}