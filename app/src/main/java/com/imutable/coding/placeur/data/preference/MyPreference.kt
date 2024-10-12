package com.imutable.coding.placeur.data.preference

import android.content.Context
import android.preference.PreferenceManager
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MyPreference @Inject constructor(@ApplicationContext context : Context){
    val prefs = PreferenceManager.getDefaultSharedPreferences(context)

    fun getJwtToken(): String {
        return prefs.getString(JWT_TOKEN, "")!!
    }
    fun setJwtToken(query: String) {
        prefs.edit().putString(JWT_TOKEN, query).apply()
    }

    companion object {
        const val JWT_TOKEN = "jwtToken"
    }

}

