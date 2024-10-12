package com.imutable.coding.placeur.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.imutable.coding.placeur.model.Place.Companion.TABLE_NAME

@Entity(tableName = TABLE_NAME)
data class Place(


    var placeId: Long? = 0,
    @PrimaryKey
    var placeName: String,
    var placeDescription: String? = null,
    var placeLat: String? = null,
    var placeLong: String? = null,
    var categoryId: Long,
    var categoryName: String? = null,
    var placeImageUrl: String? = null,
    var placeRating: Float
) {
    companion object {
        const val TABLE_NAME = "place_info"
    }
}
