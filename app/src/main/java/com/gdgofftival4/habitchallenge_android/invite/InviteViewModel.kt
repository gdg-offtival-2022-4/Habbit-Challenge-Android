package com.gdgofftival4.habitchallenge_android.invite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gdgofftival4.habitchallenge_android.common.EventLiveData
import com.gdgofftival4.habitchallenge_android.common.HabitChallengeConfig
import com.gdgofftival4.habitchallenge_android.common.MutableEventLiveData
import com.gdgofftival4.habitchallenge_android.home.adapter.CategoryImg
import com.gdgofftival4.habitchallenge_android.network.RetrofitClient
import com.gdgofftival4.habitchallenge_android.network.onSuccess
import com.gdgofftival4.habitchallenge_android.network.toApiResponse
import com.gdgofftival4.habitchallenge_android.room.JoinRoomRequest
import com.gdgofftival4.habitchallenge_android.room.RoomService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class InviteViewModel(
    private val roomService: RoomService = RetrofitClient.instance.create(RoomService::class.java),
    private val config: HabitChallengeConfig = HabitChallengeConfig
) : ViewModel() {

    private val _inviteUiModel = MutableStateFlow(InviteUiModel())
    val inviteUiModel: StateFlow<InviteUiModel>
        get() = _inviteUiModel

    private val _inviteEvent = MutableEventLiveData<InviteEvent>()
    val inviteEvent: EventLiveData<InviteEvent>
        get() = _inviteEvent

    var roomId: String = ""

    fun loadData(roomId: String) {
        this.roomId = roomId
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                roomService.fetchRoomMetaData(roomId).toApiResponse()
            }.onSuccess {
                _inviteUiModel.emit(
                    InviteUiModel(
                        category = CategoryImg.parse(it.category),
                        title = it.title,
                        description = it.content
                    )
                )
            }
        }
    }

    fun joinRoom() {
        val userId = config.userId
        if (userId == null) {
            _inviteEvent.event = InviteEvent.NeedLogin
        } else {
            viewModelScope.launch {
                withContext(Dispatchers.IO) {
                    roomService.joinRoom(JoinRoomRequest(room_id = roomId, user_id = userId))
                        .toApiResponse()
                }.onSuccess {
                    val model = inviteUiModel.value
                    _inviteEvent.event = InviteEvent.Success(
                        roomId = roomId,
                        title = model.title,
                        description = model.description,
                    )
                }
            }
        }
    }
}

data class InviteUiModel(
    val category: CategoryImg = CategoryImg.OTHER,
    val title: String = "",
    val description: String = "",
)

sealed class InviteEvent {
    object NeedLogin : InviteEvent()
    data class Success(
        val roomId: String,
        val title: String,
        val description: String,
    ) : InviteEvent()
}