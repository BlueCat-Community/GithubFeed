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

package com.bluecat.githubfeed.presenters

import androidx.lifecycle.LiveData
import com.bluecat.core.BasePresenter
import com.bluecat.githubfeed.api.ApiResponse
import com.bluecat.githubfeed.api.NetworkModule
import com.bluecat.githubfeed.model.GithubUser
import com.bluecat.githubfeed.persistence.PreferenceComponent_PrefComponent
import com.bluecat.githubfeed.persistence.Preference_UserInfo
import com.bluecat.githubfeed.viewTypes.MainActivityView
import com.skydoves.preferenceroom.InjectPreference
import timber.log.Timber

class MainPresenter : BasePresenter<MainActivityView>() {

    @InjectPreference
    lateinit var userInfo: Preference_UserInfo
    private val usersService = NetworkModule.userService

    init {
        PreferenceComponent_PrefComponent.getInstance().inject(this)
        Timber.d("Initialize MainPresenter.")
    }

    fun getHelloMessage(): String {
        return "hello, GitHub Feed!"
    }

    fun fetchUserInfo(username: String): LiveData<ApiResponse<GithubUser>> {
        return this.usersService.fetchUser(username)
    }
}
