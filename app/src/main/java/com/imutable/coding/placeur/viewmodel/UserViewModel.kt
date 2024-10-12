package com.imutable.coding.placeur.viewmodel

import android.app.Application
import android.view.View
import androidx.lifecycle.*
import com.imutable.coding.placeur.data.preference.MyPreference
import com.imutable.coding.placeur.data.repository.UserRepository
import com.imutable.coding.placeur.model.User
import com.imutable.coding.placeur.model.UserAuth
import com.imutable.coding.placeur.util.NetworkResult
import kotlinx.coroutines.flow.collect
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor
    (
    private val repository: UserRepository,
    private val preference: MyPreference,
    application: Application
) : AndroidViewModel(application) {

    private val _registerResponse: MutableLiveData<NetworkResult<User>> = MutableLiveData()
    val registerResponse: LiveData<NetworkResult<User>> = _registerResponse

    private val _authResponse: MutableLiveData<NetworkResult<UserAuth>> = MutableLiveData()
    val authResponse: LiveData<NetworkResult<UserAuth>> = _authResponse

    private val _user: MutableLiveData<User> = MutableLiveData()
    val user: LiveData<User> = _user


    fun registerUserResponse(user: User) = viewModelScope.launch {
        repository.registerUser(user).collect { values ->
            _registerResponse.value = values
        }
    }

    fun authenticateUserResponse(userAuth: UserAuth) = viewModelScope.launch {
        repository.authenticateUser(userAuth).collect { values ->
            _authResponse.value = values
            values.let { it ->
                it.data.let {
                    if (it != null) {
                        preference.setJwtToken(it.jwtToken)
                    }
                }
            }
        }
    }


    fun saveUserData(user: User) = viewModelScope.launch {
        repository.saveUser(user)
    }


}