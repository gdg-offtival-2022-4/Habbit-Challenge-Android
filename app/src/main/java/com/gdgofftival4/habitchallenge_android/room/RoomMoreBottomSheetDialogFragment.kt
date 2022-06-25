package com.gdgofftival4.habitchallenge_android.room

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import com.gdgofftival4.habitchallenge_android.databinding.FragmentMoreBinding
import com.gdgofftival4.habitchallenge_android.extension.copyToClipboard
import com.gdgofftival4.habitchallenge_android.extension.toast
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
            context?.copyToClipboard("inviteLink", "habitrabit://invite?roomId=${viewModel.roomId}")
            Toast.makeText(context, "초대 링크가 복사되었습니다.", Toast.LENGTH_SHORT).show()
            dismissAllowingStateLoss()
        }

        binding.buttonExit.setOnClickListener {
            viewModel.exitRoom()
            dismissAllowingStateLoss()
        }
    }
}