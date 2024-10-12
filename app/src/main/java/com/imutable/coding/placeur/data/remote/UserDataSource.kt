package com.imutable.coding.placeur.data.remote

import androidx.lifecycle.LiveData
import com.imutable.coding.placeur.data.remote.services.UserService
import com.imutable.coding.placeur.model.User
import com.imutable.coding.placeur.model.UserAuth
import javax.inject.Inject


class UserDataSource @Inject constructor(private val userService: UserService){

    suspend fun registerUser(user: User) =  userService.registerUser(user)

    suspend fun authenticateUser(userAuth: UserAuth) =  userService.authenticateUser(userAuth)
}