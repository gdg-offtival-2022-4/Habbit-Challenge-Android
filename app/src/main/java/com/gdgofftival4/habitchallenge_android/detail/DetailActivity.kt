package com.gdgofftival4.habitchallenge_android.detail

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.viewModels
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.gdgofftival4.habitchallenge_android.R
import com.gdgofftival4.habitchallenge_android.base.BaseBindingActivity
import com.gdgofftival4.habitchallenge_android.databinding.ActivityDetailBinding
import com.gdgofftival4.habitchallenge_android.extension.repeatOnStart
import com.gdgofftival4.habitchallenge_android.extension.setCircleImageUri
import com.gdgofftival4.habitchallenge_android.extension.setImageUri
import kotlinx.coroutines.launch

class DetailActivity : BaseBindingActivity<ActivityDetailBinding>(ActivityDetailBinding::inflate) {

    private val viewModel: DetailViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val roomId = intent.getStringExtra(EXTRA_ROOM_ID).orEmpty()
        val postId = intent.getStringExtra(EXTRA_POST_ID).orEmpty()
        viewModel.getDetail(roomId, postId)

        repeatOnStart {
            launch {
                viewModel.detailUiModel.collect {
                    binding.userName.text = it.nickname
                    binding.userRank.text = String.format("D+%d", it.point)
                    binding.userImage.setCircleImageUri(it.userImageUri, R.drawable.ic_profile_default)
                    binding.dateTxt.text = it.createdDate
                    binding.detailImg.setImageUri(it.postImageUri)
                    binding.okBtn.text = String.format("칭찬해! %d", it.goodCount)
                    binding.noBtn.text = String.format("다시해 %d", it.badCount)
                }
            }
        }

        binding.backBtn.setOnClickListener {
            finish()
        }

        binding.okBtn.setOnClickListener {
            viewModel.onClickGood()
        }

        binding.noBtn.setOnClickListener {
            viewModel.onClickBad()
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