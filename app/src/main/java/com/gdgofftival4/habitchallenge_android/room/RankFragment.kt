package com.gdgofftival4.habitchallenge_android.room

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.gdgofftival4.habitchallenge_android.R
import com.gdgofftival4.habitchallenge_android.databinding.ActivityEditProfieBinding.inflate


class RankFragment : Fragment(R.layout.fragment_rank) {
    private var binding: ViewBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = inflate(layoutInflater)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}