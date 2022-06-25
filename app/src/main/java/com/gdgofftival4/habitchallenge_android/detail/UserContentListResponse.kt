package com.gdgofftival4.habitchallenge_android.detail

import android.net.Uri

data class UserContentListResponse(
    val user: UserResponse,
    val grouped_posts: List<DatePostResponse>
)

data class UserResponse(
    val rank: Int,
    val user_image_url: String,
    val nickname: String,
    val point: Int
)

data class DatePostResponse(
    val date: String,
    val post: List<PostResponse>
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
    grouped_posts.forEach {
        list.addAll(it.toUiModel(onContentClick))
    }
    return UserContentListUiModel(
        profileImageUri = Uri.parse(user.user_image_url),
        ranking = user.rank,
        nickName = user.nickname,
        combo = user.point,
        userContentItems = list
    )
}

fun DatePostResponse.toUiModel(onContentClick: (postId: String) -> Unit): List<UserContentItem> {
    val list = mutableListOf<UserContentItem>()
    list.add(UserContentItem.UserContentDateUiModel(date))
    list.addAll(post.map {
        it.toUiModel(
            onClick = { postId ->
                onContentClick(postId)
            }
        )
    })
    return list
}

fun PostResponse.toUiModel(onClick: (postId: String) -> Unit): UserContentItem.UserContentContentUiModel {
    return UserContentItem.UserContentContentUiModel(
        imageUri = Uri.parse(post_image_url),
        state = UserContentState.parse(status),
        onContentClick = { onClick.invoke(post_id) }
    )
}