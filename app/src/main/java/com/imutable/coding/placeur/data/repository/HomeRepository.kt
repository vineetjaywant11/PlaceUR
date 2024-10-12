package com.imutable.coding.placeur.data.repository

import com.imutable.coding.placeur.data.local.dao.CategoryDao
import com.imutable.coding.placeur.data.local.dao.PlaceDao
import com.imutable.coding.placeur.data.remote.CategoryDataSource
import com.imutable.coding.placeur.data.remote.PlaceDataSource
import com.imutable.coding.placeur.model.BaseApiResponse
import com.imutable.coding.placeur.model.Category
import com.imutable.coding.placeur.model.Place
import com.imutable.coding.placeur.util.NetworkResult
import dagger.hilt.android.scopes.ActivityRetainedScoped
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

@ActivityRetainedScoped
class HomeRepository @Inject constructor(
    private val placeDataSource: PlaceDataSource,
    private val categoryDataSource: CategoryDataSource,
    private val categoryDao: CategoryDao,
    private val placeDao: PlaceDao
) : BaseApiResponse() {

    suspend fun fetchCategories(): Flow<NetworkResult<List<Category>>> {
        return flow<NetworkResult<List<Category>>> {
            emit(safeApiCall { categoryDataSource.fetchAllCategories() })
        }
    }

    suspend fun fetchPlaces(): Flow<NetworkResult<List<Place>>> {
        return flow<NetworkResult<List<Place>>> {
            emit(safeApiCall { placeDataSource.fetchAllPlaces() })
        }
    }

    fun getAllCategories(): Flow<List<Category>> {
        return categoryDao.getAllCategories()
    }

    suspend fun saveCategory(category: Category) = categoryDao.addCategory(category)

    suspend fun savePlace(place: Place) = placeDao.addPlace(place)

}