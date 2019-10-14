package com.bluecat.githubfeed.login

import com.bluecat.core.BaseView

interface LoginActivityView : BaseView {
    fun onLoginSuccess()

    fun onLoginFailure()
}