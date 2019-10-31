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

import android.content.Context
import android.app.Dialog
import android.content.Intent
import android.content.pm.ActivityInfo
import android.os.Build
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
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

    var progress: Dialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        initBaseView(this)
    }

    override fun initializeUI() {
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        supportActionBar?.hide()
        window.statusBarColor = resources.getColor(R.color.splash_statusbar_color)

        (getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager)
            .toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY)

        presenter.checkLoginSession()
        OTPEdit.visibility = View.GONE

        progress = Dialog(this).apply {
            title = "Loading"
            setCancelable(false)
        }

        loginBtn.setOnClickListener {
            showProgress()
            loginAction()
        }


        passwordEdit.setOnKeyListener { v, keyCode, event ->
            if (event.action == KeyEvent.ACTION_DOWN
                && keyCode == KeyEvent.KEYCODE_ENTER
            ) {
                showProgress()
                loginAction()
                return@setOnKeyListener true
            }
            return@setOnKeyListener false
        }

        logoutBtn.setOnClickListener {
            showProgress()
            this.presenter.logout()
        }

    }

    private fun setRelatedViewEnable(flag: Boolean) =
        runOnUiThread {
            loginBtn.isEnabled = flag
            usernameEdit.isEnabled = flag
            passwordEdit.isEnabled = flag
        }

    private fun loginAction() {
        setRelatedViewEnable(false)

        this.presenter.authenticateUser(
            usernameEdit.text.toString(),
            passwordEdit.text.toString(),
            OTPEdit.text.toString()
        )
    }

    override fun onLoginSuccess(name: String?) {
        dismissProgress()
        Toast.makeText(this, "안녕하세요. $name 님.", Toast.LENGTH_SHORT).show()
        logoutBtn.visibility = View.VISIBLE
        startActivity(Intent(this, MainActivity::class.java))
        overridePendingTransition(R.anim.abc_fade_in, R.anim.not_move_activity)
        //finish()
    }

    override fun onLoginFailure(state: String?, needOTP: Boolean) {
        dismissProgress()
        Toast.makeText(this, "Login Failure : $state", Toast.LENGTH_SHORT).show()
        setRelatedViewEnable(true)
        if (needOTP) {
            OTPEdit.visibility = View.VISIBLE
        }
    }

    override fun onLogoutSuccess() {
        dismissProgress()
        Toast.makeText(this, "로그아웃 완료.", Toast.LENGTH_SHORT).show()
        setRelatedViewEnable(true)
        logoutBtn.visibility = View.GONE
    }

    override fun showProgress() =
        runOnUiThread { progress?.show() }


    override fun dismissProgress() = runOnUiThread {
        progress?.dismiss()
    }


    override fun setRequestedOrientation(requestedOrientation: Int) {
        if (Build.VERSION.SDK_INT != Build.VERSION_CODES.O) {
            super.setRequestedOrientation(requestedOrientation)
        }
    }
}
