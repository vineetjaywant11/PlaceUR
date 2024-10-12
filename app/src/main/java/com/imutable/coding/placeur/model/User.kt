package com.imutable.coding.placeur.model

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.imutable.coding.placeur.model.User.Companion.TABLE_NAME

@Entity(tableName = TABLE_NAME)
data class User(

    var username: String? = null,
    @PrimaryKey
    var mobile: String,
    var password: String? = null,
    var email: String? = null,
) {
    companion object {
        const val TABLE_NAME = "user_info"
    }
}
