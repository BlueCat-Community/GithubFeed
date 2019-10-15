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

package com.bluecat.githubfeed.util

import android.util.Base64
import org.json.JSONObject

object AuthUtil {

    const val BAD_CREDENTIAL = 0
    const val NEED_TWO_FACTOR = 1

    fun basic(username: String, password: String): String {
        return "Basic " + Base64.encodeToString("$username:$password".toByteArray(), Base64.NO_WRAP)
    }

    fun getFailureCause(message: String?): Int {
        val cause = JSONObject(message?:"{}").get("message") as String
        return when (cause.contains("OTP")) {
            true -> NEED_TWO_FACTOR
            false -> BAD_CREDENTIAL
        }
    }
}
