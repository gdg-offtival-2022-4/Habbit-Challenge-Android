package com.gdgofftival4.habitchallenge_android.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gdgofftival4.habitchallenge_android.detail.model.DetailResponse
import com.gdgofftival4.habitchallenge_android.detail.model.GetDetailService
import com.gdgofftival4.habitchallenge_android.network.RetrofitClient
import kotlinx.coroutines.launch

class DetailViewModel(
    private val detailService: GetDetailService = RetrofitClient.instance.create(GetDetailService::class.java)
) : ViewModel() {

    private var roomId: String = ""
    private var postId: String = ""

    private val _detailUiModel = MutableLiveData<DetailResponse>()
    val detailUiModel: LiveData<DetailResponse>
        get() = _detailUiModel

    fun getDetail(roomId: String, postId: String) {
        this.roomId = roomId
        this.postId = postId
        viewModelScope.launch {
            with(detailService.getDetailt(roomId, postId)) {
                if(this.isSuccessful){
                    _detailUiModel.postValue(this.body())
                }
            }
        }
    }
}