package com.gdgofftival4.habitchallenge_android.room

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gdgofftival4.habitchallenge_android.network.RetrofitClient
import com.gdgofftival4.habitchallenge_android.register.RegisterUiModel
import com.gdgofftival4.habitchallenge_android.room.model.GetRankService
import com.gdgofftival4.habitchallenge_android.room.model.GetRecordService
import com.gdgofftival4.habitchallenge_android.room.model.RankUiResponse
import com.gdgofftival4.habitchallenge_android.room.model.RecordResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class RankViewModel(
    private val rankService: GetRankService = RetrofitClient.instance.create(GetRankService::class.java),
    private val recoreService: GetRecordService = RetrofitClient.instance.create(GetRecordService::class.java)
) : ViewModel() {

    private val _rankUiModel = MutableLiveData<List<RankUiResponse>>()
    val rankUiModel: LiveData<List<RankUiResponse>>
        get() = _rankUiModel

    fun getRankList(idx: String) {
        viewModelScope.launch {
            with(rankService.getRankList(idx)) {
                if(this.isSuccessful){
                    _rankUiModel.postValue(this.body())
                }
            }
        }
    }

    private val _recordUiModel = MutableLiveData<List<RecordResponse>>()
    val recordUiModel: LiveData<List<RecordResponse>>
        get() = _recordUiModel

    fun getRecordList(idx: String) {
        viewModelScope.launch {
            with(recoreService.getRecordList(idx)) {
                if(this.isSuccessful){
                    _recordUiModel.postValue(this.body()!!.data)
                }
            }
        }
    }

    private val _roomIdModel = MutableStateFlow(RoomIdModel())
    val roomIdiModel: StateFlow<RoomIdModel>
        get() = _roomIdModel

    fun setRoomId(roomId: String){
        _roomIdModel.update {
            it.copy(roomId = roomId)
        }
    }
}

data class RoomIdModel(
    val roomId: String = ""
)
