package com.gdgofftival4.habitchallenge_android.room

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.gdgofftival4.habitchallenge_android.addroom.RegisterViewModel
import com.gdgofftival4.habitchallenge_android.databinding.FragmentRankBinding
import com.gdgofftival4.habitchallenge_android.room.adapter.RankAdapter
import com.gdgofftival4.habitchallenge_android.room.model.GetRankService
import com.gdgofftival4.habitchallenge_android.room.model.RankResponse
import com.gdgofftival4.habitchallenge_android.room.model.RankUiResponse
import retrofit2.Response


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

        val dummy = mutableListOf<RankUiResponse>()
        val item = RankUiResponse(1, "test", "user name", 32)
        for(i in 0..10){
            dummy.add(item)
        }
        rankAdapter.addAll(dummy)
    }

}