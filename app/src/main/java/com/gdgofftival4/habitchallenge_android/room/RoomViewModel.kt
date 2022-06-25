package com.gdgofftival4.habitchallenge_android.room

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gdgofftival4.habitchallenge_android.common.EventLiveData
import com.gdgofftival4.habitchallenge_android.common.HabitChallengeConfig
import com.gdgofftival4.habitchallenge_android.common.MutableEventLiveData
import com.gdgofftival4.habitchallenge_android.network.RetrofitClient
import com.gdgofftival4.habitchallenge_android.network.onSuccess
import com.gdgofftival4.habitchallenge_android.network.toApiResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File

class RoomViewModel(
    private val roomService: RoomService = RetrofitClient.instance.create(RoomService::class.java),
    private val config: HabitChallengeConfig = HabitChallengeConfig
) : ViewModel() {

    private val _roomEvent = MutableEventLiveData<RoomEvent>()
    val roomEvent: EventLiveData<RoomEvent>
        get() = _roomEvent

    private var roomId: String = ""

    fun setRoomId(roomId: String) {
        this.roomId = roomId
    }

    fun onCameraImageResponse(imageFile: File) {
        val userId = "2"
        val multipartImage = MultipartBody.Part.createFormData(
            "image_url",
            imageFile.name,
            imageFile.asRequestBody("image/jpeg".toMediaType())
        )
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                roomService.post(
                    userId = userId,
                    roomId = roomId,
                    image = multipartImage
                ).toApiResponse()
            }.onSuccess {
                _roomEvent.event = RoomEvent.GoDetail(postId = it.post_id, roomId = roomId)
            }
        }
    }

    fun exitRoom() {
        // Todo
    }
}

sealed class RoomEvent {
    data class GoDetail(val postId: String, val roomId: String) : RoomEvent()
}