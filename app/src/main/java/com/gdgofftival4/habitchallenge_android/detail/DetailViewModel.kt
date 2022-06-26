package com.gdgofftival4.habitchallenge_android.detail

import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gdgofftival4.habitchallenge_android.detail.model.PostService
import com.gdgofftival4.habitchallenge_android.detail.model.VoteRequest
import com.gdgofftival4.habitchallenge_android.detail.model.toUiModel
import com.gdgofftival4.habitchallenge_android.network.RetrofitClient
import com.gdgofftival4.habitchallenge_android.network.onSuccess
import com.gdgofftival4.habitchallenge_android.network.toApiResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DetailViewModel(
    private val detailService: PostService = RetrofitClient.instance.create(PostService::class.java)
) : ViewModel() {

    private var roomId: String = ""
    private var postId: String = ""

    private val _detailUiModel = MutableStateFlow(DetailUiModel())
    val detailUiModel: StateFlow<DetailUiModel>
        get() = _detailUiModel

    fun getDetail(roomId: String, postId: String) {
        this.roomId = roomId
        this.postId = postId
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                detailService.fetchDetail(roomId, postId).toApiResponse()
            }.onSuccess {
                _detailUiModel.emit(it.toUiModel())
            }
        }
    }

    fun onClickGood() {
        vote(isGood = true)
    }

    fun onClickBad() {
        vote(isGood = false)
    }

    fun vote(isGood: Boolean) {
        val flag = if (isGood) 1 else 0
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                detailService.doVote(VoteRequest(post_id = postId, is_up = flag)).toApiResponse()
            }.onSuccess { response ->
                _detailUiModel.update {
                    it.copy(goodCount = response.up, badCount = response.down)
                }
            }
        }
    }
}

data class DetailUiModel(
    val point: Int = 0,
    val nickname: String = "",
    val userImageUri: Uri = Uri.EMPTY,
    val postImageUri: Uri = Uri.EMPTY,
    val status: UserContentState = UserContentState.PENDING,
    val createdDate: String = "",
    val goodCount: Int = 0,
    val badCount: Int = 0
)