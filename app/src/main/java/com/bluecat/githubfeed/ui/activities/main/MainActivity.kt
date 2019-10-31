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

import android.app.Dialog
import android.content.pm.ActivityInfo
import android.os.Build
import android.os.Bundle
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.viewpager.widget.ViewPager
import com.bluecat.core.BaseActivity
import com.bluecat.core.qualifiers.RequirePresenter
import com.bluecat.githubfeed.R
import com.bluecat.githubfeed.presenters.MainPresenter
import com.bluecat.githubfeed.ui.adapters.MainPagerAdapter
import com.bluecat.githubfeed.viewTypes.MainActivityView
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.toast

@Suppress("DEPRECATION")
@RequirePresenter(MainPresenter::class)
class MainActivity : BaseActivity<MainPresenter, MainActivityView>(),
    MainActivityView {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initBaseView(this)

        // fetch user information
        //getGitHubUserInfo("skydoves")
    }

    override fun initializeUI() {
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        supportActionBar?.hide()
        window.statusBarColor = resources.getColor(R.color.splash_statusbar_color)

        setViewPager()

        toast(this.presenter.getHelloMessage())

        // TEST
        testingsss.text = presenter.userInfo.username
    }

    override fun getGitHubUserInfo(username: String) {
        this.presenter.fetchUserInfo(username).observe(this, Observer {
        })
    }

    private fun setViewPager() {

        /** Set View pager adapter */
        viewPager.adapter = MainPagerAdapter(supportFragmentManager)

        /** Set View pager */
        viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) = Unit
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) = Unit

            override fun onPageSelected(position: Int) {
                bottom_navigation_view.menu.getItem(position).isChecked = true
            }
        })

        /** Set Bottom navigation */
        bottom_navigation_view.setOnNavigationItemSelectedListener {
            viewPager.currentItem = when (it.itemId) {
                R.id.menu_feed -> 0

                R.id.menu_2 -> 1

                R.id.menu_3 -> 2

                R.id.menu_4 -> 3

                else -> 0
            }
            true
        }

    }

    override fun setRequestedOrientation(requestedOrientation: Int) {
        if (Build.VERSION.SDK_INT != Build.VERSION_CODES.O) {
            super.setRequestedOrientation(requestedOrientation)
        }
    }
}
