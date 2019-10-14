/*
 * Copyright 2019 Team Mulro in BlueCat-Community
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.bluecat.githubfeed.api

import com.bluecat.githubfeed.persistence.PreferenceComponent_PrefComponent
import okhttp3.Interceptor
import okhttp3.Response

internal class RequestInterceptor : Interceptor {

    private val userInfo = PreferenceComponent_PrefComponent.getInstance().UserInfo()

    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val originalUrl = originalRequest.url()
        val url = originalUrl.newBuilder().build()

        val requestBuilder = originalRequest.newBuilder()
            .url(url)

        if (!userInfo.token.isNullOrEmpty()) {
            requestBuilder.addHeader("Authorization", userInfo.token)
        }

        val request = requestBuilder.build()

        return chain.proceed(request)
    }
}
