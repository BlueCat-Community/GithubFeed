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

import androidx.lifecycle.Observer
import com.bluecat.core.BasePresenter
import com.bluecat.githubfeed.api.NetworkModule
import com.bluecat.githubfeed.persistence.PreferenceComponent_PrefComponent
import com.bluecat.githubfeed.persistence.Preference_UserInfo
import com.bluecat.githubfeed.util.AuthUtil
import com.bluecat.githubfeed.viewTypes.LoginActivityView
import com.skydoves.preferenceroom.InjectPreference
import timber.log.Timber

class LoginPresenter : BasePresenter<LoginActivityView>() {


    private val loginService = NetworkModule.loginService

    init {
        PreferenceComponent_PrefComponent.getInstance().inject(this)
        Timber.d("Initialize LoginPresenter.")
    }

    fun authenticateUser(username: String, password: String, otpCode: String) {
        val token = AuthUtil.basic(username, password)
        this.loginService.authenticateUser(token, otpCode)
            .observe(this.baseView,
                Observer {
                    when {
                        it.isSuccessful -> {
                            AuthUtil.login(token, it.body?.name)
                            this.baseView.onLoginSuccess(it.body?.name)
                        }
                        it.code in 400..500 -> it.message?.let { message ->
                            when (AuthUtil.getFailureCause(message)) {
                                AuthUtil.BAD_CREDENTIAL -> this.baseView.onLoginFailure(
                                    "Sign in failed",
                                    false
                                )
                                AuthUtil.NEED_TWO_FACTOR -> this.baseView.onLoginFailure(
                                    "Two factors OTP is required",
                                    true
                                )
                            }
                        }
                        else -> this.baseView.onLoginFailure("Unknown error", false)
                    }
                })
    }

    fun checkLoginSession() {
        when (AuthUtil.hasLoginSession()) {
            true -> this.baseView.onLoginSuccess(AuthUtil.sessionUsername())
        }
    }

    fun logout() {
        when (AuthUtil.logout()) {
            true -> this.baseView.onLogoutSuccess()
            else -> {
                Timber.d("Logout failed")
            }
        }
    }
}
