package com.bluecat.githubfeed.util

import android.util.Base64
import org.json.JSONObject
import timber.log.Timber

object AuthUtil {
    const val BAD_CREDENTIAL = 0
    const val NEED_TWO_FACTOR = 1

    fun basic(username: String, password: String): String {
        return "Basic " + Base64.encodeToString("$username:$password".toByteArray(), Base64.NO_WRAP)
    }

    fun getFailureCause(message: String?): Int {
        val cause = JSONObject(message).get("message") as String
        return when (cause.contains("OTP")) {
            true -> NEED_TWO_FACTOR
            false -> BAD_CREDENTIAL
        }
    }
}