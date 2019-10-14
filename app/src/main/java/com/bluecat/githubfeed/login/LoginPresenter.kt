package com.bluecat.githubfeed.login

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.bluecat.core.BasePresenter
import com.bluecat.githubfeed.api.NetworkModule
import com.bluecat.githubfeed.util.AuthUtil
import timber.log.Timber

class LoginPresenter : BasePresenter<LoginActivityView>() {
    private val loginService = NetworkModule.loginService

    init {
        Timber.d("Initialize LoginPresenter.")
    }

    fun authenticateUser(username: String, password: String, otpCode: String?){
        this.loginService.authenticateUser(AuthUtil.basic(username,password),otpCode).observe(this.baseView as LifecycleOwner,
            Observer {
                if(it.isSuccessful){
                    this.baseView.onLoginSuccess(it.body?.name)
                } else if(it.code in 400..500){

                    when(AuthUtil.getFailureCause(it.message)){
                        AuthUtil.BAD_CREDENTIAL -> this.baseView.onLoginFailure("Sign in failed",false)
                        AuthUtil.NEED_TWO_FACTOR -> this.baseView.onLoginFailure("Two factors OTP is required",true)
                    }

                } else {
                    this.baseView.onLoginFailure("Unknown error",false)
                }
            })
    }
}