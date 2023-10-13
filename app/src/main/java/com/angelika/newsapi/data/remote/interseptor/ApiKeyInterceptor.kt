package com.angelika.newsapi.data.remote.interseptor

import okhttp3.Interceptor
import okhttp3.Response

class ApiKeyInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
        val url =
            request.url.newBuilder().addQueryParameter("apiKey", "da56408ba59243f494b82cfa9a1ba6ca")
                .build()
        request = request.newBuilder().url(url).build()
        return chain.proceed(request)
    }
}