package com.gdgofftival4.habitchallenge_android.room

import android.net.Uri
import android.util.Log
import androidx.lifecycle.ViewModel

class RoomViewModel: ViewModel() {

    fun onCameraImageResponse(imageUri: Uri) {
        // Todo
        Log.d("TESTT", "성공 $imageUri")
    }
}