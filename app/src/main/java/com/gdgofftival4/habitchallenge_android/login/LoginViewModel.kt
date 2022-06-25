package com.gdgofftival4.habitchallenge_android.login

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gdgofftival4.habitchallenge_android.common.EventLiveData
import com.gdgofftival4.habitchallenge_android.common.HabitChallengeConfig
import com.gdgofftival4.habitchallenge_android.common.MutableEventLiveData
import com.gdgofftival4.habitchallenge_android.login.data.LoginRequest
import com.gdgofftival4.habitchallenge_android.login.data.LoginService
import com.gdgofftival4.habitchallenge_android.network.RetrofitClient
import com.gdgofftival4.habitchallenge_android.network.onFailure
import com.gdgofftival4.habitchallenge_android.network.onSuccess
import com.gdgofftival4.habitchallenge_android.network.toApiResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class LoginViewModel(
    private val loginService: LoginService = RetrofitClient.instance.create(LoginService::class.java),
    private val config: HabitChallengeConfig = HabitChallengeConfig
) : ViewModel() {

    private val _loginUiModel = MutableStateFlow(LoginUiModel())
    val loginUiModel: StateFlow<LoginUiModel>
        get() = _loginUiModel

    private val _loginEvent = MutableEventLiveData<LoginEvent>()
    val loginEvent: EventLiveData<LoginEvent>
        get() = _loginEvent

    fun onChangeEmail(email: String) {
        _loginUiModel.update {
            it.copy(email = email)
        }
    }

    fun onChangePassword(password: String) {
        _loginUiModel.update {
            it.copy(password = password)
        }
    }

    fun onClickLoginButton() {
        val model = loginUiModel.value
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                loginService.login(
                    LoginRequest(id = model.email, pw = model.password)
                ).toApiResponse()
            }.onSuccess {
                config.userId = it.user_id
                _loginEvent.event = LoginEvent.Success
            }.onFailure {
                Log.e(javaClass.simpleName, it.stackTraceToString())
                _loginEvent.event = LoginEvent.Failure(it.message.orEmpty())
            }
        }
    }
}

data class LoginUiModel(
    val email: String = "",
    val password: String = "",
)

sealed class LoginEvent {
    object Success: LoginEvent()
    data class Failure(val message: String): LoginEvent()
}