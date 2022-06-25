package com.gdgofftival4.habitchallenge_android.detail

import android.net.Uri
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gdgofftival4.habitchallenge_android.common.EventLiveData
import com.gdgofftival4.habitchallenge_android.common.MutableEventLiveData
import com.gdgofftival4.habitchallenge_android.detail.model.MetaDetailModel
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

class DetailViewModel() : ViewModel() {

    private var metaDetailModel: MetaDetailModel? = null

    fun setDetailMode(metaDetailModel: MetaDetailModel?) {
        this.metaDetailModel = metaDetailModel
        if (metaDetailModel == null) {
            // Todo: 유저 정보 api 요청

        }
    }
}