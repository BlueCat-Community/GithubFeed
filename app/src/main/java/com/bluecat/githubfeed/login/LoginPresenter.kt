package com.bluecat.githubfeed.login

import com.bluecat.core.BasePresenter
import timber.log.Timber

class LoginPresenter:BasePresenter<LoginActivityView>() {

    init {
        Timber.d("Initialize MainPresenter.")
    }

    fun checkLoginData(id:String, pw:String)
    {
        TODO()
        /** 로그인을 해야합니다. */
    }
}