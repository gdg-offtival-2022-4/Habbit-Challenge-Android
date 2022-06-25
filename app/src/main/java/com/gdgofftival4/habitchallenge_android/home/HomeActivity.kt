package com.gdgofftival4.habitchallenge_android.home

import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import com.gdgofftival4.habitchallenge_android.base.BaseBindingActivity
import com.gdgofftival4.habitchallenge_android.databinding.ActivityHomeBinding
import com.gdgofftival4.habitchallenge_android.home.adapter.RoomAdapter
import com.gdgofftival4.habitchallenge_android.home.model.Rooms

class HomeActivity : BaseBindingActivity<ActivityHomeBinding>(ActivityHomeBinding::inflate) {
    lateinit var roomAdapter: RoomAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        roomAdapter = RoomAdapter()
        val item = Rooms("test", 1, "room title", "test", "test", "test")

        for(i in 0..10){
            roomAdapter.addItem(item)
        }
        roomAdapter.notifyDataSetChanged()

        binding.roomRecycler.run {
            adapter = roomAdapter
            layoutManager = GridLayoutManager(context, 2)
            setHasFixedSize(true)
        }

        binding.addBtn.setOnClickListener {

        }

        binding.userBtn.setOnClickListener {

        }
    }
}