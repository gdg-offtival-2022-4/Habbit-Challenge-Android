package com.gdgofftival4.habitchallenge_android.login

import android.content.Intent
import android.os.Bundle
import com.gdgofftival4.habitchallenge_android.base.BaseBindingActivity
import com.gdgofftival4.habitchallenge_android.common.HabitChallengeConfig
import com.gdgofftival4.habitchallenge_android.databinding.ActivityLoginBinding

class LoginActivity : BaseBindingActivity<ActivityLoginBinding>(ActivityLoginBinding::inflate) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.registerBtn.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }

    }
}