package com.gdgofftival4.habitchallenge_android.detail

import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gdgofftival4.habitchallenge_android.common.EventLiveData
import com.gdgofftival4.habitchallenge_android.common.MutableEventLiveData
import com.gdgofftival4.habitchallenge_android.network.RetrofitClient
import com.gdgofftival4.habitchallenge_android.network.onSuccess
import com.gdgofftival4.habitchallenge_android.network.toApiResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class UserContentListViewModel(
    private val userContentListService: UserContentListService = RetrofitClient.instance.create(
        UserContentListService::class.java
    )
) : ViewModel() {

    private val _userContentListUiModel = MutableStateFlow(UserContentListUiModel())
    val userContentListUiModel: StateFlow<UserContentListUiModel>
        get() = _userContentListUiModel

    private val _userContentListEvent = MutableEventLiveData<UserContentListEvent>()
    val userContentListEvent: EventLiveData<UserContentListEvent>
        get() = _userContentListEvent

    fun loadContents(userId: String, roomId: String) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                userContentListService.fetchUserContentList(userId, roomId).toApiResponse()
            }.onSuccess { response ->
                _userContentListUiModel.update {
                    response.toUiModel(
                        onContentClick = {
                            onContentClick(it)
                        }
                    )
                }
            }
        }
//
//        _userContentListUiModel.update {
//            UserContentListUiModel(
//                profileImageUri = Uri.parse("https://item.kakaocdn.net/do/493188dee481260d5c89790036be0e66113e2bd2b7407c8202a97d2241a96625"),
//                ranking = 4,
//                nickName = "아무닉네임",
//                userContentItems = listOf(
//                    UserContentItem.UserContentDateUiModel("2022년 6월"),
//                    UserContentItem.UserContentContentUiModel(
//                        imageUri = Uri.parse("https://item.kakaocdn.net/do/493188dee481260d5c89790036be0e668b566dca82634c93f811198148a26065"),
//                        state = UserContentState.PENDING,
//                        onContentClick = {
//                            onContentClick(1)
//                        }
//                    ),
//                    UserContentItem.UserContentContentUiModel(
//                        imageUri = Uri.parse("https://item.kakaocdn.net/do/493188dee481260d5c89790036be0e66effd194bae87d73dd00522794070855d"),
//                        state = UserContentState.ACCEPT,
//                        onContentClick = {
//                            onContentClick(2)
//                        }
//                    ),
//                    UserContentItem.UserContentContentUiModel(
//                        imageUri = Uri.parse("https://item.kakaocdn.net/do/493188dee481260d5c89790036be0e66113e2bd2b7407c8202a97d2241a96625"),
//                        state = UserContentState.REJECT,
//                        onContentClick = {
//                            onContentClick(3)
//                        }
//                    ),
//                    UserContentItem.UserContentContentUiModel(
//                        imageUri = Uri.parse("https://item.kakaocdn.net/do/493188dee481260d5c89790036be0e66c37d537a8f2c6f426591be6b8dc7b36a"),
//                        state = UserContentState.ACCEPT,
//                        onContentClick = {
//                            onContentClick(4)
//                        }
//                    ),
//                    UserContentItem.UserContentContentUiModel(
//                        imageUri = Uri.parse("https://item.kakaocdn.net/do/493188dee481260d5c89790036be0e669cbcbe2de7f4969efc79ab353e0c19e8"),
//                        state = UserContentState.ACCEPT,
//                        onContentClick = {
//                            onContentClick(5)
//                        }
//                    ),
//                )
//            )
//        }
    }

    private fun onContentClick(contentId: String) {
        _userContentListEvent.event = UserContentListEvent.Detail(contentId)
    }
}

data class UserContentListUiModel(
    val profileImageUri: Uri = Uri.EMPTY,
    val ranking: Int = -1,
    val nickName: String = "",
    val combo: Int = 0,
    val userContentItems: List<UserContentItem> = emptyList(),
)

sealed class UserContentListEvent {
    data class Detail(val postId: String) : UserContentListEvent()
}