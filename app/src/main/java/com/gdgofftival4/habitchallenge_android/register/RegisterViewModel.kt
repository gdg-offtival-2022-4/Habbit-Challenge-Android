package com.gdgofftival4.habitchallenge_android.register

import androidx.lifecycle.ViewModel
import com.gdgofftival4.habitchallenge_android.common.EventLiveData
import com.gdgofftival4.habitchallenge_android.common.MutableEventLiveData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class RegisterViewModel: ViewModel() {

    private val _registerUiModel = MutableStateFlow(RegisterUiModel())
    val registerUiModel: StateFlow<RegisterUiModel>
        get() = _registerUiModel

    private val _registerEvent = MutableEventLiveData<RegisterEvent>()
    val registerEvent: EventLiveData<RegisterEvent>
        get() = _registerEvent

    fun onChangeEmail(email: String) {
        _registerUiModel.update {
            it.copy(email = email)
        }
    }

    fun onChangePassword(password: String) {
        _registerUiModel.update {
            it.copy(password = password)
        }
    }

    fun onChangeCheckPassword(checkPassword: String) {
        _registerUiModel.update {
            it.copy(checkPassword = checkPassword)
        }
    }

    fun onClickNextButton() {
        val uiModel = registerUiModel.value
        if (uiModel.password != uiModel.checkPassword) {
            _registerEvent.event = RegisterEvent.NotMatchPassword
            return
        }
        val metaRegisterModel = MetaRegisterModel(
            email = uiModel.email,
            password = uiModel.password
        )
        _registerEvent.event = RegisterEvent.Next(metaRegisterModel)
    }
}

data class RegisterUiModel(
    val email: String = "",
    val password: String = "",
    val checkPassword: String = ""
)

sealed class RegisterEvent {
    object NotMatchPassword: RegisterEvent()
    data class Next(val metaRegisterModel: MetaRegisterModel): RegisterEvent()
}
