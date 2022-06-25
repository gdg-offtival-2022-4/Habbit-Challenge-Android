package com.gdgofftival4.habitchallenge_android.home

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import com.gdgofftival4.habitchallenge_android.base.BaseBindingActivity
import com.gdgofftival4.habitchallenge_android.common.HabitChallengeConfig
import com.gdgofftival4.habitchallenge_android.databinding.ActivityHomeBinding
import com.gdgofftival4.habitchallenge_android.databinding.ActivitySplashBinding
import com.gdgofftival4.habitchallenge_android.login.LoginActivity

@SuppressLint("CustomSplashScreen")
class HomeActivity : BaseBindingActivity<ActivityHomeBinding>(ActivityHomeBinding::inflate) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
}