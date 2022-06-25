package com.gdgofftival4.habitchallenge_android.addroom

import android.content.ClipDescription
import android.net.Uri
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gdgofftival4.habitchallenge_android.addroom.model.AddRoomRequest
import com.gdgofftival4.habitchallenge_android.addroom.model.Category
import com.gdgofftival4.habitchallenge_android.addroom.model.CategoryUiModel
import com.gdgofftival4.habitchallenge_android.profile.EditProfileUiModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class RegisterViewModel : ViewModel() {

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
