package com.gdgofftival4.habitchallenge_android.room

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.viewpager2.widget.ViewPager2
import com.gdgofftival4.habitchallenge_android.base.BaseBindingActivity
import com.gdgofftival4.habitchallenge_android.databinding.ActivityRoomBinding
import com.gdgofftival4.habitchallenge_android.extension.toast
import com.gdgofftival4.habitchallenge_android.profile.EditProfileActivity
import com.gdgofftival4.habitchallenge_android.room.adapter.RoomViewpagerFragmentAdapter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class RoomActivity : BaseBindingActivity<ActivityRoomBinding>(ActivityRoomBinding::inflate) {

    private val viewModel: RoomViewModel by viewModels()
    private val rankViewModel: RankViewModel by viewModels()

    private val cameraImageDelegate: CameraImageDelegate by lazy { CameraImageDelegate(this) }

    private val cameraActivityResultLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            with(result) {
                val imageUri = currentCameraImage
                if (imageUri == null || resultCode != Activity.RESULT_OK) {
                    toast("실패")
                } else {
                    viewModel.onCameraImageResponse(imageUri)
                }
            }
        }

    private var currentCameraImage: Uri? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val roomId = intent.getStringExtra("roomId")
        val roomTitle = intent.getStringExtra("roomTitle")
        val roomContents = intent.getStringExtra("roomContents")

        rankViewModel.setRoomId(roomId.toString())

        binding.roomTitle.text = roomTitle
        binding.roomContents.text = roomContents

        val viewPager: ViewPager2 = binding.viewPager
        val tabLayout: TabLayout = binding.tabLayout

        val viewpagerFragmentAdapter = RoomViewpagerFragmentAdapter(this)
        viewPager.adapter = viewpagerFragmentAdapter

        val tabTitles = listOf("순위", "기록")
        // 2. TabLayout과 ViewPager2를 연결하고, TabItem의 메뉴명을 설정한다.
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = tabTitles[position]
        }.attach()

        binding.moreBtn.setOnClickListener {
            val imageUri = cameraImageDelegate.createImageUri().also {
                currentCameraImage = it
            }
            Intent(MediaStore.ACTION_IMAGE_CAPTURE).also { takePictureIntent ->
                takePictureIntent.resolveActivity(packageManager)?.also {
                    takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri)
                    cameraActivityResultLauncher.launch(takePictureIntent)
                }
            }
        }

        binding.informationBtn.setOnClickListener {
            EditProfileActivity.startEditProfileActivity(this)
        }

        binding.backBtn.setOnClickListener {
            finish()
        }
    }
}