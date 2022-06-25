package com.gdgofftival4.habitchallenge_android.home

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.gdgofftival4.habitchallenge_android.addroom.AddRoomActivity
import com.gdgofftival4.habitchallenge_android.base.BaseBindingActivity
import com.gdgofftival4.habitchallenge_android.common.HabitChallengeConfig
import com.gdgofftival4.habitchallenge_android.databinding.ActivityHomeBinding
import com.gdgofftival4.habitchallenge_android.home.adapter.RoomAdapter
import com.gdgofftival4.habitchallenge_android.home.model.RoomUiResponse
import com.gdgofftival4.habitchallenge_android.profile.EditProfileActivity
import com.gdgofftival4.habitchallenge_android.room.RoomActivity

class HomeActivity : BaseBindingActivity<ActivityHomeBinding>(ActivityHomeBinding::inflate) {

    private val viewModel: HomeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        viewModel.getHomeList(HabitChallengeConfig.userId.toString())
        viewModel.getHomeList("1")

        val roomAdapter = RoomAdapter(
            onItemClick = { roomId, roomTitle, roomContents ->
                val intent = Intent(this, RoomActivity::class.java)
                intent.putExtra("roomId", roomId)
                intent.putExtra("roomTitle", roomTitle)
                intent.putExtra("roomContents", roomContents)
                startActivity(intent)
            }
        )

        binding.roomRecycler.run {
            adapter = roomAdapter
            layoutManager = GridLayoutManager(context, 2)
            setHasFixedSize(true)
        }

        viewModel.homeUiModel.observe(this) {
            roomAdapter.addAll(it)
        }

        binding.addBtn.setOnClickListener {
            startActivity(Intent(this, AddRoomActivity::class.java))
        }

        binding.userBtn.setOnClickListener {
            EditProfileActivity.startEditProfileActivity(this)
        }
    }
}