package com.gdgofftival4.habitchallenge_android.room

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gdgofftival4.habitchallenge_android.network.RetrofitClient
import com.gdgofftival4.habitchallenge_android.room.model.GetRankService
import com.gdgofftival4.habitchallenge_android.room.model.RankUiResponse
import kotlinx.coroutines.launch

class RankViewModel(
    private val rankService: GetRankService = RetrofitClient.instance.create(GetRankService::class.java)
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
}
