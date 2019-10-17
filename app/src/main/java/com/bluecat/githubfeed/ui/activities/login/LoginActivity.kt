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

package com.bluecat.githubfeed.ui.activities.login

import android.content.Intent
import android.content.pm.ActivityInfo
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.bluecat.core.BaseActivity
import com.bluecat.core.qualifiers.RequirePresenter
import com.bluecat.githubfeed.R
import com.bluecat.githubfeed.presenters.LoginPresenter
import com.bluecat.githubfeed.ui.activities.main.MainActivity
import com.bluecat.githubfeed.viewTypes.LoginActivityView
import kotlinx.android.synthetic.main.activity_login.*

@Suppress("DEPRECATION")
@RequirePresenter(LoginPresenter::class)
class LoginActivity : BaseActivity<LoginPresenter, LoginActivityView>(),
    LoginActivityView {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        initBaseView(this)
    }

    override fun initializeUI() {
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        supportActionBar?.hide()
        window.statusBarColor = resources.getColor(R.color.splash_statusbar_color)

        presenter.checkLoginSession()
        OTPEdit.visibility = View.GONE

        loginBtn.setOnClickListener {
            val username = usernameEdit.text.toString()
            val password = passwordEdit.text.toString()
            val otpCode = OTPEdit.text.toString()

            this.presenter.authenticateUser(username, password, otpCode)
        }

        logoutBtn.setOnClickListener {
            this.presenter.logout()
        }
    }

    override fun onLoginSuccess(name: String?) {
        Toast.makeText(this, "안녕하세요. $name 님.", Toast.LENGTH_SHORT).show()
        usernameEdit.isEnabled = false
        passwordEdit.isEnabled = false
        loginBtn.isEnabled = false
        logoutBtn.visibility = View.VISIBLE
        startActivity(Intent(this, MainActivity::class.java))
        overridePendingTransition(R.anim.abc_fade_in, R.anim.not_move_activity)
        //finish()
    }

    override fun onLoginFailure(state: String?, needOTP: Boolean) {
        Toast.makeText(this, "Login Failure : $state", Toast.LENGTH_SHORT).show()
        if (needOTP) {
            OTPEdit.visibility = View.VISIBLE
        }
    }

    override fun onLogoutSuccess() {
        Toast.makeText(this, "로그아웃 완료.", Toast.LENGTH_SHORT).show()
        usernameEdit.isEnabled = true
        passwordEdit.isEnabled = true
        loginBtn.isEnabled = true
        logoutBtn.visibility = View.GONE
    }

    override fun setRequestedOrientation(requestedOrientation: Int) {
        if (Build.VERSION.SDK_INT != Build.VERSION_CODES.O) {
            super.setRequestedOrientation(requestedOrientation)
        }
    }
}
