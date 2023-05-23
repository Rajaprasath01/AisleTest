package com.example.aisletest.retrofit

import com.example.aisletest.model.*
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*

interface ApiInterface {


    @POST("users/phone_number_login")
    suspend fun loginWithPhoneNumber(@Body initialCredential: InitialCredential): Response<Login>

    @POST("users/verify_otp")
    suspend fun loginWithOtp(@Body credentials: Credentials): Response<Token>

    @GET("users/test_profile_list")
     fun getProfileDetails(@Header("Authorization") token: String): Response<JSONObject>


}