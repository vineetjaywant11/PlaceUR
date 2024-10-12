package com.imutable.coding.placeur.data.remote.services

import com.imutable.coding.placeur.model.User
import com.imutable.coding.placeur.model.UserAuth
import com.imutable.coding.placeur.util.Constants
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface UserService {

    @POST(Constants.REGISTER_CONTROLLER)
    suspend fun registerUser(@Body user: User): Response<User>

    @POST(Constants.AUTH_CONTROLLER)
    suspend fun authenticateUser(@Body userAuth: UserAuth): Response<UserAuth>
}