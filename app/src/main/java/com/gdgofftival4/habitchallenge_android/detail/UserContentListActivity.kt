package com.gdgofftival4.habitchallenge_android.detail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.DisplayMetrics
import androidx.activity.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gdgofftival4.habitchallenge_android.R
import com.gdgofftival4.habitchallenge_android.base.BaseBindingActivity
import com.gdgofftival4.habitchallenge_android.databinding.ActivityUserContentListBinding
import com.gdgofftival4.habitchallenge_android.extension.observeEvent
import com.gdgofftival4.habitchallenge_android.extension.repeatOnStart
import com.gdgofftival4.habitchallenge_android.extension.setCircleImageUri
import kotlinx.coroutines.launch

class UserContentListActivity :
    BaseBindingActivity<ActivityUserContentListBinding>(ActivityUserContentListBinding::inflate) {

    private val viewModel: UserContentListViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val userId = intent.getStringExtra(EXTRA_USER_ID)
        val roomId = intent.getStringExtra(EXTRA_ROOM_ID)

        if (userId == null || roomId == null) {
            finish()
            return
        }

        repeatOnStart {
            launch {
                viewModel.userContentListUiModel.collect {
                    val adapter =
                        binding.recyclerviewHabitContentList.adapter as? UserContentListRecyclerViewAdapter
                    adapter?.replaceAll(it.userContentItems)
                    binding.profileImageContentList.setCircleImageUri(
                        it.profileImageUri,
                        R.drawable.ic_profile_default
                    )
                    binding.rankContentList.text = String.format("%d위", it.ranking)
                    binding.nicknameContentList.text = it.nickName
                    binding.habitComboContentList.text = String.format("+%d", it.combo)
                }
            }
        }

        observeEvent(viewModel.userContentListEvent) {
            when (it) {
                is UserContentListEvent.Detail -> {
                    DetailActivity.startDetailActivity(
                        context = this,
                        roomId = roomId,
                        postId = it.postId
                    )
                }
            }
        }

        binding.recyclerviewHabitContentList.initRecyclerView()
        viewModel.loadContents(userId = userId, roomId = roomId)
    }

    private fun RecyclerView.initRecyclerView() {
        val displayMetrics = DisplayMetrics().apply {
            windowManager.defaultDisplay.getMetrics(this)
        }
        val itemWidth = displayMetrics.widthPixels / GRID_HORIZONTAL_ITEM_COUNT
        val detailRecyclerViewAdapter = UserContentListRecyclerViewAdapter(itemWidth)
        adapter = detailRecyclerViewAdapter
        layoutManager = GridLayoutManager(context, GRID_HORIZONTAL_ITEM_COUNT).apply {
            spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
                override fun getSpanSize(position: Int): Int {
                    val viewType = detailRecyclerViewAdapter.getItemViewType(position)
                    return if (viewType == UserContentListRecyclerViewAdapter.VIEW_TYPE_DATE) {
                        GRID_HORIZONTAL_ITEM_COUNT
                    } else {
                        1
                    }
                }
            }
        }
    }

    companion object {
        private const val GRID_HORIZONTAL_ITEM_COUNT = 3

        private const val EXTRA_USER_ID = "userId"
        private const val EXTRA_ROOM_ID = "roomId"

        fun startUserContentListActivity(context: Context, userId: String, roomId: String) {
            context.startActivity(Intent(context, UserContentListActivity::class.java).apply {
                putExtra(EXTRA_USER_ID, userId)
                putExtra(EXTRA_ROOM_ID, roomId)
            })
        }
    }
}