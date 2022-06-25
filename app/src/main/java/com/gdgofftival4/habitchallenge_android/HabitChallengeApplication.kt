package com.gdgofftival4.habitchallenge_android

import android.app.Application

class HabitChallengeApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    companion object {
        lateinit var instance: HabitChallengeApplication
        fun getApplication(): HabitChallengeApplication {
            return instance
        }
    }
}