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

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object NetworkModule {

    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(RequestInterceptor())
        .build()

    private val retrofit: Retrofit
    val userService: UsersService
    val loginService: LoginService

    init {
        this.retrofit = Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl("https://api.github.com/")
            .addCallAdapterFactory(LiveDataCallAdapterFactory())
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        this.userService = retrofit.create(UsersService::class.java)
        this.loginService = retrofit.create(LoginService::class.java)
    }
}
