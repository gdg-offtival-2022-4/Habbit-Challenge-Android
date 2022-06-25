package com.gdgofftival4.habitchallenge_android.invite

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import com.gdgofftival4.habitchallenge_android.base.BaseBindingActivity
import com.gdgofftival4.habitchallenge_android.databinding.ActivityInviteBinding
import com.gdgofftival4.habitchallenge_android.extension.repeatOnStart
import com.gdgofftival4.habitchallenge_android.home.HomeActivity
import kotlinx.coroutines.launch

class InviteActivity : BaseBindingActivity<ActivityInviteBinding>(ActivityInviteBinding::inflate) {

    private val viewModel: InviteViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        repeatOnStart {
            launch {
                viewModel.inviteUiModel.collect {
                    binding.textTitleInvite.text = it.title
                    binding.textDescriptionInvite.text = it.description
                    binding.imageCoverInvite.setImageResource(it.category.imageRes)
                }
            }
        }

        val action = intent?.action
        val data = intent?.data

        if (action == Intent.ACTION_VIEW) {
            val roomId = data?.getQueryParameter("roomId")
            roomId?.let { viewModel.loadData(it) }
        }

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