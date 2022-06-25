package com.gdgofftival4.habitchallenge_android.addroom

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gdgofftival4.habitchallenge_android.addroom.model.Category
import com.gdgofftival4.habitchallenge_android.addroom.model.CategoryUiModel

class RegisterViewModel : ViewModel() {

    private val _categoryUiModel = MutableLiveData<List<CategoryUiModel>>()
    val categoryUiModel: LiveData<List<CategoryUiModel>>
        get() = _categoryUiModel

    fun clickCategory(idx: Int) {
        val before = _categoryUiModel.value?:return
        val after = before.mapIndexed { index, categoryUiModel ->
            if(index == idx){
                categoryUiModel.copy(isSelected = true)
            }
            else{
                categoryUiModel.copy(isSelected = false)
            }
        }
        _categoryUiModel.value = after
    }

    init {
        val categoryList = Category.values().map {
            CategoryUiModel(category = it, isSelected = false)
        }
        _categoryUiModel.value = categoryList
    }
}
