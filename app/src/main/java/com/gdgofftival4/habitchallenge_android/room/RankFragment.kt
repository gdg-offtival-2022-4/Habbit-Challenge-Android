package com.gdgofftival4.habitchallenge_android.room

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.gdgofftival4.habitchallenge_android.databinding.FragmentRankBinding
import com.gdgofftival4.habitchallenge_android.detail.UserContentListActivity
import com.gdgofftival4.habitchallenge_android.room.adapter.RankAdapter
import com.gdgofftival4.habitchallenge_android.room.model.RankUiResponse


class RankFragment : Fragment() {

    private val viewModel: RankViewModel by lazy {
        ViewModelProvider(requireActivity()).get(RankViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return FragmentRankBinding.inflate(inflater, container, false).root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val roomId = viewModel.roomIdiModel.value.roomId

        // rank api 호출
        viewModel.getRankList(roomId)

        val binding = FragmentRankBinding.bind(view)

        val rankAdapter = RankAdapter(
            onItemClick = {
                UserContentListActivity.startUserContentListActivity(
                    context = view.context,
                    userId = it,
                    roomId = roomId
                )
            }
        )
        binding.rankRecycler.run {
            adapter = rankAdapter
            layoutManager = LinearLayoutManager(context).apply {
                orientation = LinearLayoutManager.VERTICAL
            }
            setHasFixedSize(true)
        }

        viewModel.rankUiModel.observe(viewLifecycleOwner) {
            val response = mutableListOf<RankUiResponse>()
            it.forEach { item ->
                response.add(item)
            }
            rankAdapter.addAll(response)
        }
    }

}