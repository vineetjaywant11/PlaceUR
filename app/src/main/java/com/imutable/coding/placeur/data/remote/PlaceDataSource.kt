package com.imutable.coding.placeur.data.remote

import com.imutable.coding.placeur.data.remote.services.PlaceService
import com.imutable.coding.placeur.model.Place
import javax.inject.Inject

class PlaceDataSource @Inject constructor(private val placeService: PlaceService){

    suspend fun fetchAllPlaces() = placeService.fetchAllPlaces()

    suspend fun savePlace(place: Place) = placeService.savePlace(place)
}