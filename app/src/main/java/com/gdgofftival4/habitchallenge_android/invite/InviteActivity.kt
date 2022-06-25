package com.gdgofftival4.habitchallenge_android.invite

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import com.gdgofftival4.habitchallenge_android.base.BaseBindingActivity
import com.gdgofftival4.habitchallenge_android.databinding.ActivityInviteBinding
import com.gdgofftival4.habitchallenge_android.extension.observeEvent
import com.gdgofftival4.habitchallenge_android.extension.repeatOnStart
import com.gdgofftival4.habitchallenge_android.home.HomeActivity
import com.gdgofftival4.habitchallenge_android.login.LoginActivity
import com.gdgofftival4.habitchallenge_android.room.RoomActivity
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

        observeEvent(viewModel.inviteEvent) {
            when (it) {
                is InviteEvent.NeedLogin -> {
                    startActivity(Intent(this, LoginActivity::class.java))
                }
                is InviteEvent.Success -> {
                    startActivity(Intent(this, RoomActivity::class.java).apply {
                        putExtra("roomId", it.roomId)
                        putExtra("roomTitle", it.title)
                        putExtra("roomContents", it.description)
                    })
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
            viewModel.joinRoom()
        }

        binding.buttonRejectInvite.setOnClickListener {
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