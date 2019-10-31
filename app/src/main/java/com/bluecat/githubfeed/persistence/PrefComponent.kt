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

package com.bluecat.githubfeed.persistence

import com.bluecat.githubfeed.presenters.LoginPresenter
import com.bluecat.githubfeed.presenters.MainPresenter
import com.bluecat.githubfeed.util.AuthUtil
import com.skydoves.preferenceroom.PreferenceComponent

@PreferenceComponent(entities = [UserInfo::class])
interface PrefComponent {
    fun inject(target__: LoginPresenter)
    fun inject(target__: MainPresenter)
    fun inject(target__: AuthUtil)
}