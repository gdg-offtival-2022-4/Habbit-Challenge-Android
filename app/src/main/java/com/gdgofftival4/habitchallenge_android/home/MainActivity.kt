package com.gdgofftival4.habitchallenge_android.home

import android.content.Intent
import android.os.Bundle
import com.gdgofftival4.habitchallenge_android.base.BaseBindingActivity
import com.gdgofftival4.habitchallenge_android.common.HabitChallengeConfig
import com.gdgofftival4.habitchallenge_android.databinding.ActivityMainBinding

class MainActivity : BaseBindingActivity<ActivityMainBinding>(ActivityMainBinding::inflate) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (HabitChallengeConfig.userId == -1) {
            startActivity(Intent(this, LoginActivity::class.java))
        } else {
            // Todo : 메인 화면 이동
        }
    }
}