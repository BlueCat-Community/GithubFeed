package com.bluecat.githubfeed.login

import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.bluecat.core.BaseActivity
import com.bluecat.core.qualifiers.RequirePresenter
import com.bluecat.githubfeed.R
import kotlinx.android.synthetic.main.activity_login.*

@RequirePresenter(LoginPresenter::class)
class LoginActivity : BaseActivity<LoginPresenter, LoginActivityView>(), LoginActivityView {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        initBaseView(this)
    }

    override fun initializeUI() {
        OTPEdit.visibility = View.GONE

        loginBtn.setOnClickListener {
            val username = usernameEdit.text.toString()
            val password = passwordEdit.text.toString()
            val otpCode = OTPEdit.text.toString()

            this.presenter.authenticateUser(username, password, otpCode)
        }
    }

    override fun onLoginSuccess(name: String?) {
        Toast.makeText(this, "안녕하세요. $name 님.", Toast.LENGTH_SHORT).show()
    }

    override fun onLoginFailure(state: String?, needOTP: Boolean) {
        Toast.makeText(this, "Login Failure : $state", Toast.LENGTH_SHORT).show()
        if (needOTP) {
            OTPEdit.visibility = View.VISIBLE
        }
    }
}
