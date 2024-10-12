package com.imutable.coding.placeur.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.imutable.coding.placeur.model.Category.Companion.TABLE_NAME

@Entity(tableName = TABLE_NAME)
data class Category(
    @PrimaryKey
    val categoryId: Int,
    val categoryName: String,
    val categoryImageUrl: String,
    val categorySecondaryImageUrl: String
) {
    companion object {
        const val TABLE_NAME = "categories"
    }
}

