package com.gdgofftival4.habitchallenge_android.splash

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import com.gdgofftival4.habitchallenge_android.base.BaseBindingActivity
import com.gdgofftival4.habitchallenge_android.common.HabitChallengeConfig
import com.gdgofftival4.habitchallenge_android.databinding.ActivitySplashBinding
import com.gdgofftival4.habitchallenge_android.home.HomeActivity
import com.gdgofftival4.habitchallenge_android.login.LoginActivity
import com.gdgofftival4.habitchallenge_android.room.RoomActivity

@SuppressLint("CustomSplashScreen")
class SplashActivity : BaseBindingActivity<ActivitySplashBinding>(ActivitySplashBinding::inflate) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (HabitChallengeConfig.userId == null) {
            startActivity(Intent(this, HomeActivity::class.java))
        } else {
            // Todo : 메인 화면 이동
            startActivity(Intent(this, HomeActivity::class.java))
        }
    }
}