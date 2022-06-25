package com.gdgofftival4.habitchallenge_android.network

import retrofit2.Call
import retrofit2.await

sealed class ApiResponse<out T> {
    data class Success<T>(val data: T) : ApiResponse<T>()
    data class Failure(val throwable: Throwable) : ApiResponse<Nothing>()

    fun getOrNull(): T? = when (this) {
        is Success -> data
        else -> null
    }

    fun getThrowableOrNull(): Throwable? = when (this) {
        is Failure -> throwable
        else -> null
    }
}

inline fun <T> ApiResponse<T>.onSuccess(
    block: (data: T) -> Unit
): ApiResponse<T> = also {
    getOrNull()?.let(block)
}

inline fun <T> ApiResponse<T>.onFailure(
    block: (throwable: Throwable) -> Unit
): ApiResponse<T> = also {
    getThrowableOrNull()?.let(block)
}

suspend fun <T : Any> Call<T>.toApiResponse(): ApiResponse<T> {
    return try {
        ApiResponse.Success(await())
    } catch (t: Throwable) {
        ApiResponse.Failure(t)
    }
}
