package com.gdgofftival4.habitchallenge_android.detail

import androidx.lifecycle.ViewModel
import com.gdgofftival4.habitchallenge_android.detail.model.MetaDetailModel

class DetailViewModel() : ViewModel() {

    private var roomId: String = ""
    private var postId: String = ""

    fun setDetailMode(roomId: String, postId: String) {
        this.roomId = roomId
        this.postId = postId
    }
}