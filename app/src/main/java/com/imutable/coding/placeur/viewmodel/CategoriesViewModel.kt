package com.imutable.coding.placeur.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.imutable.coding.placeur.data.repository.HomeRepository
import com.imutable.coding.placeur.model.Category
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoriesViewModel @Inject constructor(
    private val repository: HomeRepository,
    application: Application
) : AndroidViewModel(application) {

    private val _allCategoriesList: MutableLiveData<List<Category>> = MutableLiveData()
    val allCategoriesList: LiveData<List<Category>> = _allCategoriesList

    fun getAllCategories() = viewModelScope.launch {
        repository.getAllCategories().collect {
            _allCategoriesList.value = it
        }
    }

}