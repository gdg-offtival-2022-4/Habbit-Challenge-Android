package com.gdgofftival4.habitchallenge_android.detail

import android.net.Uri
import android.util.Log
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class UserContentListViewModel: ViewModel() {

    private val _userContentListUiModel = MutableStateFlow(UserContentListUiModel())
    val userContentListUiModel: StateFlow<UserContentListUiModel>
        get() = _userContentListUiModel

    fun loadContents(userId: Int) {
        // Todo
        _userContentListUiModel.update {
            UserContentListUiModel(
                profileImageUri = Uri.parse("https://item.kakaocdn.net/do/493188dee481260d5c89790036be0e66113e2bd2b7407c8202a97d2241a96625"),
                ranking = 4,
                nickName = "아무닉네임",
                userContentItems = listOf(
                    UserContentItem.UserContentDateUiModel("2022년 6월"),
                    UserContentItem.UserContentContentUiModel(
                        imageUri = Uri.parse("https://item.kakaocdn.net/do/493188dee481260d5c89790036be0e668b566dca82634c93f811198148a26065"),
                        state = UserContentState.PENDING,
                        onContentClick = {
                            onContentClick(1)
                        }
                    ),
                    UserContentItem.UserContentContentUiModel(
                        imageUri = Uri.parse("https://item.kakaocdn.net/do/493188dee481260d5c89790036be0e66effd194bae87d73dd00522794070855d"),
                        state = UserContentState.ACCEPT,
                        onContentClick = {
                            onContentClick(2)
                        }
                    ),
                    UserContentItem.UserContentContentUiModel(
                        imageUri = Uri.parse("https://item.kakaocdn.net/do/493188dee481260d5c89790036be0e66113e2bd2b7407c8202a97d2241a96625"),
                        state = UserContentState.REJECT,
                        onContentClick = {
                            onContentClick(3)
                        }
                    ),
                    UserContentItem.UserContentContentUiModel(
                        imageUri = Uri.parse("https://item.kakaocdn.net/do/493188dee481260d5c89790036be0e66c37d537a8f2c6f426591be6b8dc7b36a"),
                        state = UserContentState.ACCEPT,
                        onContentClick = {
                            onContentClick(4)
                        }
                    ),
                    UserContentItem.UserContentContentUiModel(
                        imageUri = Uri.parse("https://item.kakaocdn.net/do/493188dee481260d5c89790036be0e669cbcbe2de7f4969efc79ab353e0c19e8"),
                        state = UserContentState.ACCEPT,
                        onContentClick = {
                            onContentClick(5)
                        }
                    ),
                )
            )
        }
    }

    private fun onContentClick(contentId: Int) {
        Log.d(javaClass.simpleName, "onClick: contentId($contentId)")
    }
}

data class UserContentListUiModel(
    val profileImageUri: Uri = Uri.EMPTY,
    val ranking: Int = -1,
    val nickName: String = "",
    val combo: Int = 0,
    val userContentItems: List<UserContentItem> = emptyList(),
)