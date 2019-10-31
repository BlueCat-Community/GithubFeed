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

package com.bluecat.githubfeed.model

import com.google.gson.annotations.SerializedName

data class Feeds(
    val timeline_url: String,
    val user_url: String,
    val current_user_public_url: String,
    val current_user_url: String,
    val current_user_actor_url: String,
    val current_user_organization_url: String,
    val current_user_organizations_url: List<String>,
    val security_advisories_url: String,
    @SerializedName("_links")
    val links: Links
)

data class Links(
    val timeline: HrefObject,
    val user: HrefObject,
    val current_user_public: HrefObject,
    val current_user: HrefObject,
    val current_user_actor: HrefObject,
    val current_user_organization: HrefObject,
    val current_user_organizations: List<HrefObject>,
    val security_advisories: HrefObject
)

data class HrefObject(val href: String, val type: String)
