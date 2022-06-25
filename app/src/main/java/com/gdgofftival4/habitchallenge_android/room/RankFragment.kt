package com.gdgofftival4.habitchallenge_android.room

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewbinding.ViewBinding
import com.gdgofftival4.habitchallenge_android.R
import com.gdgofftival4.habitchallenge_android.databinding.ActivityEditProfieBinding.inflate
import com.gdgofftival4.habitchallenge_android.databinding.FragmentRankBinding
import com.gdgofftival4.habitchallenge_android.home.adapter.RoomAdapter
import com.gdgofftival4.habitchallenge_android.home.model.Room
import com.gdgofftival4.habitchallenge_android.room.adapter.RankAdapter
import com.gdgofftival4.habitchallenge_android.room.model.Rank


class RankFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return FragmentRankBinding.inflate(inflater, container, false).root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentRankBinding.bind(view)

        val rankAdapter = RankAdapter(
            onItemClick = {
                // Todo
            }
        )
        binding.rankRecycler.run {
            adapter = rankAdapter
            layoutManager = LinearLayoutManager(context).apply {
                orientation = LinearLayoutManager.VERTICAL
            }
            setHasFixedSize(true)
        }

        val dummy = mutableListOf<Rank>()
        val item = Rank(1, "test", "user name", 32)
        for(i in 0..10){
            dummy.add(item)
        }
        rankAdapter.addAll(dummy)
    }

}