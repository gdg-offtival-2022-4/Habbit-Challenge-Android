package com.gdgofftival4.habitchallenge_android.room

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.gdgofftival4.habitchallenge_android.databinding.FragmentMoreBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class RoomMoreBottomSheetDialogFragment: BottomSheetDialogFragment() {

    private val viewModel: RoomViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return FragmentMoreBinding.inflate(inflater, container, false).root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentMoreBinding.bind(view)

        binding.buttonCopyLink.setOnClickListener {
            viewModel.
        }

        binding.buttonExit.setOnClickListener {
            viewModel.exitRoom()
        }
    }
}