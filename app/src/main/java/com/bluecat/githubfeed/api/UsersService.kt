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

import androidx.lifecycle.LiveData
import com.bluecat.githubfeed.model.GithubUser
import retrofit2.http.GET
import retrofit2.http.Path

interface UsersService {

    // https://developer.github.com/v3/users/
    @GET("/users/{username}")
    fun fetchUser(@Path("username") username: String): LiveData<ApiResponse<GithubUser>>
}
