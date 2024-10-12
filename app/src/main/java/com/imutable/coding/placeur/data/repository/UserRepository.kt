package com.imutable.coding.placeur.data.repository


import com.imutable.coding.placeur.data.local.dao.UserDao
import com.imutable.coding.placeur.data.remote.UserDataSource
import com.imutable.coding.placeur.model.BaseApiResponse
import com.imutable.coding.placeur.model.User
import com.imutable.coding.placeur.model.UserAuth
import com.imutable.coding.placeur.util.NetworkResult
import dagger.hilt.android.scopes.ActivityRetainedScoped
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject


@ActivityRetainedScoped
class UserRepository @Inject constructor(
    private val userDataSource: UserDataSource,
    private val userDao: UserDao
) : BaseApiResponse() {

    suspend fun registerUser(user: User): Flow<NetworkResult<User>> {
        return flow<NetworkResult<User>> {
            emit(safeApiCall { userDataSource.registerUser(user) })
        }.flowOn(Dispatchers.IO)
    }

    suspend fun authenticateUser(userAuth: UserAuth): Flow<NetworkResult<UserAuth>> {
        return flow<NetworkResult<UserAuth>> {
            emit(safeApiCall { userDataSource.authenticateUser(userAuth) })
        }.flowOn(Dispatchers.IO)
    }

    suspend fun saveUser(user: User) = userDao.addUser(user)

}