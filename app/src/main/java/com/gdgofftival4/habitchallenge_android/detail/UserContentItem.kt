package com.gdgofftival4.habitchallenge_android.detail

import android.net.Uri

sealed class UserContentItem {
    data class UserContentContentUiModel(
        val imageUri: Uri = Uri.EMPTY,
        val state: UserContentState = UserContentState.PENDING,
        val onContentClick: () -> Unit
    ): UserContentItem()

    data class UserContentDateUiModel(
        val date: String = ""
    ): UserContentItem()
}

enum class UserContentState {
    REJECT,
    ACCEPT,
    PENDING,
    ;

    companion object {
        fun parse(state: String): UserContentState {
            return values().find { it.name == state } ?: PENDING
        }
    }
}