package com.gdgofftival4.habitchallenge_android.profile

import android.net.Uri
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gdgofftival4.habitchallenge_android.common.EventLiveData
import com.gdgofftival4.habitchallenge_android.common.HabitChallengeConfig
import com.gdgofftival4.habitchallenge_android.common.MutableEventLiveData
import com.gdgofftival4.habitchallenge_android.network.RetrofitClient
import com.gdgofftival4.habitchallenge_android.network.onFailure
import com.gdgofftival4.habitchallenge_android.network.onSuccess
import com.gdgofftival4.habitchallenge_android.network.toApiResponse
import com.gdgofftival4.habitchallenge_android.register.MetaRegisterModel
import com.gdgofftival4.habitchallenge_android.register.RegisterService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File

class EditProfileViewModel(
    private val registerService: RegisterService = RetrofitClient.instance.create(RegisterService::class.java),
    private val config: HabitChallengeConfig = HabitChallengeConfig
) : ViewModel() {

    private val _profileUiModel = MutableStateFlow(EditProfileUiModel())
    val profileUiModel: StateFlow<EditProfileUiModel>
        get() = _profileUiModel

    private val _profileEvent = MutableEventLiveData<EditProfileEvent>()
    val profileEvent: EventLiveData<EditProfileEvent>
        get() = _profileEvent

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

    fun onDoneProfileEdit(imageFile: File) {
        val meta = metaRegisterModel
        if (meta != null) {
            val model = _profileUiModel.value
            val multipartImage = MultipartBody.Part.createFormData(
                "image_url",
                imageFile.name,
                imageFile.asRequestBody("image/jpeg".toMediaType())
            )
            viewModelScope.launch {
                withContext(Dispatchers.IO) {
                    registerService.register(
                        email = meta.email,
                        password = meta.password,
                        nickName = model.nickName,
                        profileImage = multipartImage
                    ).toApiResponse()
                }.onSuccess {
                    config.userId = it.user_id
                    _profileEvent.event = EditProfileEvent.Success
                }.onFailure {
                    Log.e(javaClass.simpleName, "${it.stackTrace}")
                    _profileEvent.event = EditProfileEvent.Failure(it.message.orEmpty())
                }
            }
        } else {
            // Todo: 프로필 정보 수정 요청
        }
    }
}

data class EditProfileUiModel(
    val image: Uri = Uri.EMPTY,
    val nickName: String = "",
)

sealed class EditProfileEvent {
    object Success: EditProfileEvent()
    data class Failure(val message: String): EditProfileEvent()
}