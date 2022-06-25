package com.gdgofftival4.habitchallenge_android.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gdgofftival4.habitchallenge_android.home.model.GetHomeService
import com.gdgofftival4.habitchallenge_android.home.model.RoomUiResponse
import com.gdgofftival4.habitchallenge_android.network.RetrofitClient
import com.gdgofftival4.habitchallenge_android.room.model.GetRankService
import com.gdgofftival4.habitchallenge_android.room.model.RankUiResponse
import kotlinx.coroutines.launch

class HomeViewModel(
    private val homeService: GetHomeService = RetrofitClient.instance.create(GetHomeService::class.java)
) : ViewModel() {

    private val _homeUiModel = MutableLiveData<List<RoomUiResponse>>()
    val homeUiModel: LiveData<List<RoomUiResponse>>
        get() = _homeUiModel

    fun getHomeList(userId: String) {
        viewModelScope.launch {
            with(homeService.getHommeList(userId)) {
                if(this.isSuccessful){
                    _homeUiModel.postValue(this.body())
                }
            }
        }
    }
}
