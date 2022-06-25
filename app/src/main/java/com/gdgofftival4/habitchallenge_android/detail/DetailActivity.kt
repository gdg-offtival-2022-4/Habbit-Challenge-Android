package com.gdgofftival4.habitchallenge_android.detail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.gdgofftival4.habitchallenge_android.base.BaseBindingActivity
import com.gdgofftival4.habitchallenge_android.databinding.ActivityDetailBinding
import com.gdgofftival4.habitchallenge_android.detail.model.MetaDetailModel
import com.gdgofftival4.habitchallenge_android.profile.EditProfileActivity
import com.gdgofftival4.habitchallenge_android.register.MetaRegisterModel

class DetailActivity : BaseBindingActivity<ActivityDetailBinding>(ActivityDetailBinding::inflate) {

    private val viewModel: DetailViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val metaDetailModel =
            intent.getParcelableExtra<MetaDetailModel>(EXTRA_META_DETAIL_MODEL)
        viewModel.setDetailMode(metaDetailModel)

        val roomId = intent.getStringExtra("roomId")
        val postId = intent.getStringExtra("postId")

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
        private const val EXTRA_META_DETAIL_MODEL = "metaDetailModel"

        fun startDetailActivity(
            context: Context,
            metaDetailModel: MetaDetailModel? = null
        ) {
            context.startActivity(Intent(context, DetailActivity::class.java).apply {
                putExtra(EXTRA_META_DETAIL_MODEL, metaDetailModel)
            })
        }
    }
}