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

package com.bluecat.githubfeed.ui.activities.main

import android.content.pm.ActivityInfo
import android.os.Build
import android.os.Bundle
import androidx.lifecycle.Observer
import com.bluecat.core.BaseActivity
import com.bluecat.core.qualifiers.RequirePresenter
import com.bluecat.githubfeed.R
import com.bluecat.githubfeed.presenters.MainPresenter
import com.bluecat.githubfeed.ui.adapters.MainPagerAdapter
import com.bluecat.githubfeed.viewTypes.MainActivityView
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.toast

@RequirePresenter(MainPresenter::class)
class MainActivity : BaseActivity<MainPresenter, MainActivityView>(),
    MainActivityView {

    private lateinit var pagerAdapter: MainPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initBaseView(this)

        pagerAdapter = MainPagerAdapter(supportFragmentManager)
        viewPager.adapter = pagerAdapter
//TODO
        // fetch user information
        getGitHubUserInfo("skydoves")
    }

    override fun initializeUI() {
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        supportActionBar?.hide()
        window.statusBarColor = resources.getColor(R.color.splash_statusbar_color)

        bottom_navigation_view.setOnNavigationItemSelectedListener {
            val transaction = supportFragmentManager.beginTransaction()

            /** 프레그먼트 관리는 View에서할지 Presenter에서 할지 모르겠다.*/

            return@setOnNavigationItemSelectedListener when (it.itemId) {
                R.id.menu_feed -> {
                    //TODO
                    true
                }
                else -> false
            }

        }

        toast(this.presenter.getHelloMessage())
    }

    override fun getGitHubUserInfo(username: String) {
        this.presenter.fetchUserInfo(username).observe(this, Observer {

        })
    }

    override fun setRequestedOrientation(requestedOrientation: Int) {
        if (Build.VERSION.SDK_INT != Build.VERSION_CODES.O) {
            super.setRequestedOrientation(requestedOrientation)
        }
    }
}
