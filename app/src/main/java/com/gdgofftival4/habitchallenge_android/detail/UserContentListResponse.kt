package com.gdgofftival4.habitchallenge_android.detail

import android.net.Uri

data class UserContentListResponse(
    val user: UserResponse,
    val posts: List<PostResponse>
)

data class UserResponse(
    val rank: Int,
    val image_url: String,
    val nickname: String,
    val point: Int
)

data class PostResponse(
    val post_id: String,
    val post_image_url: String,
    val status: String
)

fun UserContentListResponse.toUiModel(
    onContentClick: (postId: String) -> Unit
): UserContentListUiModel {
    val list = mutableListOf<UserContentItem>()
    list.add(UserContentItem.UserContentDateUiModel("2022년 6월"))
    list.addAll(posts.map { it.toUiModel(onContentClick) })
    return UserContentListUiModel(
        profileImageUri = Uri.parse(user.image_url),
        ranking = user.rank,
        nickName = user.nickname,
        combo = user.point,
        userContentItems = list
    )
}

fun PostResponse.toUiModel(onClick: (postId: String) -> Unit): UserContentItem.UserContentContentUiModel {
    return UserContentItem.UserContentContentUiModel(
        imageUri = Uri.parse(post_image_url),
        state = UserContentState.parse(status),
        onContentClick = { onClick.invoke(post_id) }
    )
}