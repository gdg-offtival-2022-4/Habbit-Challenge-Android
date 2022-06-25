package com.gdgofftival4.habitchallenge_android.addroom

import android.net.Uri
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gdgofftival4.habitchallenge_android.addroom.model.AddRoomRequest
import com.gdgofftival4.habitchallenge_android.addroom.model.Category
import com.gdgofftival4.habitchallenge_android.addroom.model.CategoryUiModel
import com.gdgofftival4.habitchallenge_android.common.EventLiveData
import com.gdgofftival4.habitchallenge_android.common.MutableEventLiveData
import com.gdgofftival4.habitchallenge_android.detail.model.MetaDetailModel
import com.gdgofftival4.habitchallenge_android.network.RetrofitClient
import com.gdgofftival4.habitchallenge_android.network.onFailure
import com.gdgofftival4.habitchallenge_android.network.onSuccess
import com.gdgofftival4.habitchallenge_android.network.toApiResponse
import com.gdgofftival4.habitchallenge_android.register.MetaRegisterModel
import com.gdgofftival4.habitchallenge_android.register.RegisterEvent
import com.gdgofftival4.habitchallenge_android.register.RegisterService
import com.gdgofftival4.habitchallenge_android.register.RegisterUiModel
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

class AddViewModel : ViewModel() {

    private val _categoryUiModel = MutableLiveData<List<CategoryUiModel>>()
    val categoryUiModel: LiveData<List<CategoryUiModel>>
        get() = _categoryUiModel

    fun clickCategory(idx: Int) {
        val before = _categoryUiModel.value?:return
        val after = before.mapIndexed { index, categoryUiModel ->
            if(index == idx){
                _addroomUiModel.update {
                    it.copy(category = categoryUiModel.category)
                }
                categoryUiModel.copy(isSelected = true)
            }
            else{
                categoryUiModel.copy(isSelected = false)
            }
        }
        _categoryUiModel.value = after
    }

    private val _addroomUiModel = MutableStateFlow(AddRoomRequest())
    val addroomUiModel: StateFlow<AddRoomRequest>
        get() = _addroomUiModel

    fun onDoneAddRoom() {
        Log.d(javaClass.simpleName, addroomUiModel.value.toString())
        val request = addroomUiModel.value
        with(request) {
            if(this.title != null && this.description != null && this.category != null){
                // Todo: Add Room 호출
                this.category.idx

            }
        }

    }

    fun onUpdatetitle(title: String) {
        _addroomUiModel.update {
            it.copy(title = title)
        }
    }

    fun onUpdateDescription(description: String) {
        _addroomUiModel.update {
            it.copy(description = description)
        }
    }

    init {
        val categoryList = Category.values().map {
            CategoryUiModel(category = it, isSelected = false)
        }
        _categoryUiModel.value = categoryList
    }
}