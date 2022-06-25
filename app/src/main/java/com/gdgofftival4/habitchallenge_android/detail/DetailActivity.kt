package com.gdgofftival4.habitchallenge_android.detail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.gdgofftival4.habitchallenge_android.base.BaseBindingActivity
import com.gdgofftival4.habitchallenge_android.databinding.ActivityDetailBinding

class DetailActivity : BaseBindingActivity<ActivityDetailBinding>(ActivityDetailBinding::inflate) {

    private val viewModel: DetailViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val roomId = intent.getStringExtra(EXTRA_ROOM_ID).orEmpty()
        val postId = intent.getStringExtra(EXTRA_POST_ID).orEmpty()
        Log.d("TESTT", "roomId: $roomId, postId: $postId")
        viewModel.setDetailMode(roomId, postId)

        Glide.with(this)
            .load("test")
            .transform(CenterCrop())
            .into(binding.detailImg)

        Glide.with(this)
            .load("test")
            .transform(CenterCrop(),  RoundedCorners(200))
            .into(binding.userImage)

        binding.userName.text = ""
        binding.userRank.text = "D+"

        binding.backBtn.setOnClickListener {
            finish()
        }

        binding.okBtn.setOnClickListener {
        }

        binding.noBtn.setOnClickListener {
        }
    }

    companion object {
        private const val EXTRA_ROOM_ID = "roomId"
        private const val EXTRA_POST_ID = "postId"

        fun startDetailActivity(
            context: Context,
            roomId: String,
            postId: String
        ) {
            context.startActivity(Intent(context, DetailActivity::class.java).apply {
                putExtra(EXTRA_ROOM_ID, roomId)
                putExtra(EXTRA_POST_ID, postId)
            })
        }
    }
}