package com.imutable.coding.placeur.util

import com.imutable.coding.placeur.data.preference.MyPreference
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class HeaderTokenInterceptor @Inject constructor(private val myPreference: MyPreference) : Interceptor {


    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()
        if (myPreference.getJwtToken() == null) {
            return chain.proceed(original)
        }
        val request = original.newBuilder()
            .addHeader(
                "Authorization",
                "Bearer ${myPreference.getJwtToken()}"
            )
            .method(original.method, original.body)
            .build()
        return chain.proceed(request)
    }
}