package com.gdgofftival4.habitchallenge_android.login

import android.content.Intent
import android.os.Bundle
import com.gdgofftival4.habitchallenge_android.base.BaseBindingActivity
import com.gdgofftival4.habitchallenge_android.databinding.ActivityRegisterBinding
import com.gdgofftival4.habitchallenge_android.profile.EditProfileActivity

class RegisterActivity : BaseBindingActivity<ActivityRegisterBinding>(ActivityRegisterBinding::inflate) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.okBtn.setOnClickListener {
            startActivity(Intent(this, EditProfileActivity::class.java))
        }

    }
}