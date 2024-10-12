package com.imutable.coding.placeur.data.remote.services

import com.imutable.coding.placeur.model.Place
import com.imutable.coding.placeur.util.Constants
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface PlaceService {

    @GET(Constants.PLACE_CONTROLLER)
    suspend fun fetchAllPlaces(): Response<List<Place>>

    @POST(Constants.PLACE_CONTROLLER)
    suspend fun savePlace(@Body place: Place): Response<Place>
}