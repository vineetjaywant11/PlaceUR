package com.imutable.coding.placeur.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.imutable.coding.placeur.data.repository.HomeRepository
import com.imutable.coding.placeur.model.Category
import com.imutable.coding.placeur.model.Place
import com.imutable.coding.placeur.util.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: HomeRepository,
    application: Application
) : AndroidViewModel(application) {

    private val _allCategories: MutableLiveData<NetworkResult<List<Category>>> = MutableLiveData()
    val allCategories: LiveData<NetworkResult<List<Category>>> = _allCategories

    private val _allPlaces: MutableLiveData<NetworkResult<List<Place>>> = MutableLiveData()
    val allPlaces: LiveData<NetworkResult<List<Place>>> = _allPlaces


    fun fetchAllCategories() = viewModelScope.launch {
        repository.fetchCategories().collect {
            _allCategories.value = it
            it.data.let {
                if (it != null) {
                    for (item in it) {
                        repository.saveCategory(item)
                    }
                }

            }
        }
    }

    fun fetchAllPlaces() = viewModelScope.launch {
        repository.fetchPlaces().collect {
            _allPlaces.value = it
            it.data.let {
                if (it != null) {
                    for (item in it) {
                        repository.savePlace(item)
                    }
                }

            }
        }
    }
}