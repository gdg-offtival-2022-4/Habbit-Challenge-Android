package com.gdgofftival4.habitchallenge_android.invite

import android.content.Intent
import android.os.Bundle
import com.gdgofftival4.habitchallenge_android.base.BaseBindingActivity
import com.gdgofftival4.habitchallenge_android.databinding.ActivityInviteBinding
import com.gdgofftival4.habitchallenge_android.home.HomeActivity

class InviteActivity : BaseBindingActivity<ActivityInviteBinding>(ActivityInviteBinding::inflate) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.buttonAcceptInvite.setOnClickListener {
            // Todo
        }

        binding.buttonRejectInvite.setOnClickListener {
            // Todo
            onBackPressed()
        }
    }

    override fun onBackPressed() {
        if (isTaskRoot.not()) {
            finish()
            return
        }
        startActivity(Intent(this, HomeActivity::class.java))
        finish()
    }
}