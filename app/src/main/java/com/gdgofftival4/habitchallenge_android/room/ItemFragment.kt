package com.gdgofftival4.habitchallenge_android.room

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.gdgofftival4.habitchallenge_android.databinding.FragmentItemBinding
import com.gdgofftival4.habitchallenge_android.room.adapter.PendingContentsAdapter
import com.gdgofftival4.habitchallenge_android.room.model.PendingContent


class ItemFragment : Fragment() {

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

        val pendingAdapter = PendingContentsAdapter(
            onItemClick = {
                // Todo
            }
        )
        binding.pendingContentsRecycler.run {
            adapter = pendingAdapter
            layoutManager = GridLayoutManager(context, 3)

        val dummy = mutableListOf<PendingContent>()
        val item = PendingContent(1, "test")
        for(i in 0..10){
            dummy.add(item)
        }
        pendingAdapter.addAll(dummy)
        }
    }
}