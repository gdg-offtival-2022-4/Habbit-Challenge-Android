package com.gdgofftival4.habitchallenge_android.invite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gdgofftival4.habitchallenge_android.addroom.model.Category
import com.gdgofftival4.habitchallenge_android.network.RetrofitClient
import com.gdgofftival4.habitchallenge_android.network.onSuccess
import com.gdgofftival4.habitchallenge_android.network.toApiResponse
import com.gdgofftival4.habitchallenge_android.room.RoomService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class InviteViewModel(
    private val roomService: RoomService = RetrofitClient.instance.create(RoomService::class.java)
) : ViewModel() {

    private val _inviteUiModel = MutableStateFlow(InviteUiModel())
    val inviteUiModel: StateFlow<InviteUiModel>
        get() = _inviteUiModel

    fun loadData(roomId: String) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                roomService.fetchRoomMetaData(roomId).toApiResponse()
            }.onSuccess {
                _inviteUiModel.emit(
                    InviteUiModel(
                        category = Category.parse(it.category),
                        title = it.title,
                        description = it.content
                    )
                )
            }
        }
    }
}

data class InviteUiModel(
    val category: Category = Category.OTHER,
    val title: String = "",
    val description: String = "",
)