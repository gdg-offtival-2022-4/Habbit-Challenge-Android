package com.gdgofftival4.habitchallenge_android.home

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import com.gdgofftival4.habitchallenge_android.base.BaseBindingActivity
import com.gdgofftival4.habitchallenge_android.databinding.ActivityHomeBinding
import com.gdgofftival4.habitchallenge_android.home.adapter.RoomAdapter
import com.gdgofftival4.habitchallenge_android.home.model.Room

@SuppressLint("CustomSplashScreen")
class HomeActivity : BaseBindingActivity<ActivityHomeBinding>(ActivityHomeBinding::inflate) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val item = Room("test", 1, "room title", "test", "test", "test")

        val roomAdapter = RoomAdapter(
            onItemClick = {
                // Todo
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

        }

        binding.userBtn.setOnClickListener {

        }
    }
}