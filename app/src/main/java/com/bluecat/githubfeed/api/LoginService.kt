package com.bluecat.githubfeed.api

import androidx.lifecycle.LiveData
import com.bluecat.githubfeed.model.GithubUser
import retrofit2.http.GET
import retrofit2.http.Header

interface LoginService {
    @GET("/user")
    fun authenticateUser(@Header("Authorization") basic : String, @Header("x-github-otp")otpCode : String?): LiveData<ApiResponse<GithubUser>>
}