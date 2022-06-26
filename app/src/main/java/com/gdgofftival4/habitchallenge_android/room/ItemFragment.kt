package com.gdgofftival4.habitchallenge_android.room

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.gdgofftival4.habitchallenge_android.databinding.FragmentItemBinding
import com.gdgofftival4.habitchallenge_android.detail.DetailActivity
import com.gdgofftival4.habitchallenge_android.room.adapter.PendingContentsAdapter
import com.gdgofftival4.habitchallenge_android.room.model.RecordResponse


class ItemFragment : Fragment() {

    private val viewModel: RankViewModel by lazy {
        ViewModelProvider(requireActivity()).get(RankViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return FragmentItemBinding.inflate(inflater, container, false).root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentItemBinding.bind(view)

        viewModel.getRecordList(viewModel.roomIdiModel.value.roomId)

        val pendingAdapter = PendingContentsAdapter(
            onItemClick = {
                val intent = Intent(requireContext(), DetailActivity::class.java)
                intent.putExtra("roomId", viewModel.roomIdiModel.value.roomId)
                intent.putExtra("postId", it)
                startActivity(intent)
            }
        )

        binding.pendingContentsRecycler.run {
            adapter = pendingAdapter
            layoutManager = GridLayoutManager(context, 3)
        }

        viewModel.recordUiModel.observe(viewLifecycleOwner) {
            pendingAdapter.addAll(it)
        }
    }
}