package com.bluecat.githubfeed.login

import android.content.pm.ActivityInfo
import android.os.Build
import android.os.Bundle
import com.bluecat.core.BaseActivity
import com.bluecat.core.qualifiers.RequirePresenter
import com.bluecat.githubfeed.R
import kotlinx.android.synthetic.main.activity_login.*

@RequirePresenter(LoginPresenter::class)
class LoginActivity:BaseActivity<LoginPresenter, LoginActivityView>(), LoginActivityView {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        initBaseView(this)
    }

    override fun initializeUI() {
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        supportActionBar?.hide()
        window.statusBarColor = resources.getColor(R.color.splash_statusbar_color)

        loginButton.setOnClickListener {
            val id = idBox.text.toString()
            val pw = pwBox.text.toString()

            presenter.checkLoginData(id, pw)
        }
    }

    override fun onLoginSuccess() {
        TODO()
    }

    override fun onLoginFailure() {
        TODO()
    }

    override fun setRequestedOrientation(requestedOrientation: Int) {
        if (Build.VERSION.SDK_INT != Build.VERSION_CODES.O) {
            super.setRequestedOrientation(requestedOrientation)
        }
    }
}