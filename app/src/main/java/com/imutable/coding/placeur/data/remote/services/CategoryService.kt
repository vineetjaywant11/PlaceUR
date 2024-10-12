package com.imutable.coding.placeur.data.remote.services

import com.imutable.coding.placeur.model.Category
import com.imutable.coding.placeur.util.Constants
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface CategoryService {

    @GET(Constants.CATEGORY_CONTROLLER)
    suspend fun fetchAllCategory(): Response<List<Category>>

    @POST(Constants.CATEGORY_CONTROLLER)
    suspend fun saveCategory(@Body category: Category): Response<Category>
}