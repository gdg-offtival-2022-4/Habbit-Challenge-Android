package com.gdgofftival4.habitchallenge_android.profile

import android.net.Uri
import android.util.Log
import androidx.lifecycle.ViewModel
import com.gdgofftival4.habitchallenge_android.register.MetaRegisterModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class EditProfileViewModel: ViewModel() {

    private val _profileUiModel = MutableStateFlow(EditProfileUiModel())
    val profileUiModel: StateFlow<EditProfileUiModel>
        get() = _profileUiModel

    private var metaRegisterModel: MetaRegisterModel? = null

    fun setEditMode(metaRegisterModel: MetaRegisterModel?) {
        this.metaRegisterModel = metaRegisterModel
        if (metaRegisterModel == null) {
            // Todo: 유저 정보 api 요청
        }
    }

    fun onUpdateProfileImage(uri: Uri) {
        _profileUiModel.update {
            it.copy(image = uri)
        }
    }

    fun onNickNameChanged(nickName: String) {
        _profileUiModel.update {
            it.copy(nickName = nickName)
        }
    }

    fun onDoneProfileEdit() {
        Log.d(javaClass.simpleName, profileUiModel.value.toString())
        if (metaRegisterModel != null) {
            // Todo: 회원가입 요청
            Log.d(javaClass.simpleName, metaRegisterModel.toString())
        } else {
            // Todo: 프로필 정보 수정 요청
        }
    }
}

data class EditProfileUiModel(
    val image: Uri = Uri.EMPTY,
    val nickName: String = "",
)