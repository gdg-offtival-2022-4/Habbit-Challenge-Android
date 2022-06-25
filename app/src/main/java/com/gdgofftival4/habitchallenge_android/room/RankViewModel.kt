package com.gdgofftival4.habitchallenge_android.room

import android.content.ClipDescription
import android.net.Uri
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gdgofftival4.habitchallenge_android.addroom.model.AddRoomRequest
import com.gdgofftival4.habitchallenge_android.addroom.model.Category
import com.gdgofftival4.habitchallenge_android.addroom.model.CategoryUiModel
import com.gdgofftival4.habitchallenge_android.profile.EditProfileUiModel
import com.gdgofftival4.habitchallenge_android.room.model.GetRankService
import com.gdgofftival4.habitchallenge_android.room.model.RankResponse
import com.gdgofftival4.habitchallenge_android.room.model.RankUiResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import retrofit2.Response

class RankViewModel(private val model: GetRankService) : ViewModel() {

    private val _rankUiModel = MutableLiveData<List<RankUiResponse>>()
    val rankUiModel: LiveData<List<RankUiResponse>>
        get() = _rankUiModel

    fun getRankList(idx: Int) {
        viewModelScope.launch {
            with(model.getRankList()) {
                if(this.isSuccessful){
                    _rankUiModel.postValue(this.body()!!.users)
                }
            }
        }
    }
}
