package com.aa.netflixclone.data.remote

import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject
import javax.inject.Singleton
import com.aa.netflixclone.BuildConfig

@Singleton
class AuthorizationInterceptor @Inject constructor() : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
            .newBuilder()
            .header(AUTHORIZATION,"$BEARER ${BuildConfig.TOKEN}")
            .build()
        return chain.proceed(request)
    }


    private companion object{
        const val AUTHORIZATION = "Authorization"
        const val BEARER = "bearer"
    }
}