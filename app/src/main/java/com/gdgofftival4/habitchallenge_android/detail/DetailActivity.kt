package com.gdgofftival4.habitchallenge_android.detail

import android.content.Intent
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.gdgofftival4.habitchallenge_android.base.BaseBindingActivity
import com.gdgofftival4.habitchallenge_android.databinding.ActivityDetailBinding
import com.gdgofftival4.habitchallenge_android.databinding.ActivityHomeBinding
import com.gdgofftival4.habitchallenge_android.home.adapter.RoomAdapter
import com.gdgofftival4.habitchallenge_android.home.model.Room
import com.gdgofftival4.habitchallenge_android.room.RoomActivity

class DetailActivity : BaseBindingActivity<ActivityDetailBinding>(ActivityDetailBinding::inflate) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.backBtn.setOnClickListener {
            finish()
        }

        Glide.with(this)
            .load("test")
            .transform(CenterCrop())
            .into(binding.detailImg)

        Glide.with(this)
            .load("test")
            .transform(CenterCrop())
            .into(binding.userImage)

        binding.userName.text = ""
        binding.userRank.text = ""

        binding.okBtn.setOnClickListener {
        }

        binding.noBtn.setOnClickListener {
        }
    }
}