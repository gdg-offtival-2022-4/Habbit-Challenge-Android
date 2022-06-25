package com.gdgofftival4.habitchallenge_android.home

import android.content.Intent
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import com.gdgofftival4.habitchallenge_android.addroom.AddRoomActivity
import com.gdgofftival4.habitchallenge_android.base.BaseBindingActivity
import com.gdgofftival4.habitchallenge_android.databinding.ActivityHomeBinding
import com.gdgofftival4.habitchallenge_android.home.adapter.RoomAdapter
import com.gdgofftival4.habitchallenge_android.home.model.Room
import com.gdgofftival4.habitchallenge_android.room.RoomActivity

class HomeActivity : BaseBindingActivity<ActivityHomeBinding>(ActivityHomeBinding::inflate) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val item = Room("test", 1, "room title", "test", "test", "test")

        val roomAdapter = RoomAdapter(
            onItemClick = {
                // Todo
                startActivity(Intent(this, RoomActivity::class.java))
            }
        )

        binding.roomRecycler.run {
            adapter = roomAdapter
            layoutManager = GridLayoutManager(context, 2)
            setHasFixedSize(true)
        }

        val dummy = mutableListOf<Room>()
        for(i in 0..10){
            dummy.add(item)
        }
        roomAdapter.addAll(dummy)

        binding.addBtn.setOnClickListener {
            startActivity(Intent(this, AddRoomActivity::class.java))
        }

        binding.userBtn.setOnClickListener {

        }
    }
}