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

package com.bluecat.githubfeed.main

import android.os.Bundle
import com.bluecat.core.BaseActivity
import com.bluecat.core.qualifiers.RequirePresenter
import com.bluecat.githubfeed.R
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.toast

@RequirePresenter(MainPresenter::class)
class MainActivity : BaseActivity<MainPresenter, MainActivityView>(),
    MainActivityView {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initBaseView(this)
    }

    override fun initializeUI() {
        text_hello.text = this.presenter.getHelloMessage()
        toast(this.presenter.getHelloMessage())
    }
}
