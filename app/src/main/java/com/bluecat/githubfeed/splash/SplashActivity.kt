package com.bluecat.githubfeed.splash

import com.bluecat.core.BaseActivity
import com.bluecat.core.qualifiers.RequirePresenter

@RequirePresenter(SplashPresenter::class)
class SplashActivity:BaseActivity<SplashPresenter, SplashActivityView>(), SplashActivityView {
    override fun initializeUI() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}