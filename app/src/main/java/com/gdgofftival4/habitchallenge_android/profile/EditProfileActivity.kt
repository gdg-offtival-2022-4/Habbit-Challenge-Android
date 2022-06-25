package com.gdgofftival4.habitchallenge_android.profile

import android.os.Bundle
import com.gdgofftival4.habitchallenge_android.base.BaseBindingActivity
import com.gdgofftival4.habitchallenge_android.databinding.ActivityEditProfieBinding

class EditProfileActivity : BaseBindingActivity<ActivityEditProfieBinding>(ActivityEditProfieBinding::inflate) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.backBtn.setOnClickListener {
            finish()
        }

        binding.saveBtn.setOnClickListener {
            finish()
        }
    }
}