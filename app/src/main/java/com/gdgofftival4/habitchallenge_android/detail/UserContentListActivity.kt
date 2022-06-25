package com.gdgofftival4.habitchallenge_android.detail

import android.os.Bundle
import android.util.DisplayMetrics
import androidx.activity.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gdgofftival4.habitchallenge_android.base.BaseBindingActivity
import com.gdgofftival4.habitchallenge_android.databinding.ActivityUserContentListBinding
import com.gdgofftival4.habitchallenge_android.extension.repeatOnStart
import com.gdgofftival4.habitchallenge_android.extension.setCircleImageUri
import kotlinx.coroutines.launch

class UserContentListActivity :
    BaseBindingActivity<ActivityUserContentListBinding>(ActivityUserContentListBinding::inflate) {

    private val viewModel: UserContentListViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        repeatOnStart {
            launch {
                viewModel.userContentListUiModel.collect {
                    val adapter =
                        binding.recyclerviewHabitContentList.adapter as? DetailRecyclerViewAdapter
                    adapter?.replaceAll(it.userContentItems)
                    binding.profileImageContentList.setCircleImageUri(it.profileImageUri)
                    binding.rankContentList.text = String.format("%d위", it.ranking)
                    binding.nicknameContentList.text = it.nickName
                    binding.habitComboContentList.text = String.format("+%d", it.combo)
                }
            }
        }

        binding.recyclerviewHabitContentList.initRecyclerView()
        viewModel.loadContents(2)
    }

    private fun RecyclerView.initRecyclerView() {
        val displayMetrics = DisplayMetrics().apply {
            windowManager.defaultDisplay.getMetrics(this)
        }
        val itemWidth = displayMetrics.widthPixels / GRID_HORIZONTAL_ITEM_COUNT
        val detailRecyclerViewAdapter = DetailRecyclerViewAdapter(itemWidth)
        adapter = detailRecyclerViewAdapter
        layoutManager = GridLayoutManager(context, GRID_HORIZONTAL_ITEM_COUNT).apply {
            spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
                override fun getSpanSize(position: Int): Int {
                    val viewType = detailRecyclerViewAdapter.getItemViewType(position)
                    return if (viewType == DetailRecyclerViewAdapter.VIEW_TYPE_DATE) {
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
    }
}