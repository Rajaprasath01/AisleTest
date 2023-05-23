package com.example.aisletest.repository

import android.util.Log
import com.example.aisletest.model.*
import com.example.aisletest.retrofit.ApiInterface
import com.example.aisletest.retrofit.RetrofitInstance
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header

class Repository {

    private val apiInterface: ApiInterface = RetrofitInstance.getClient().create(ApiInterface::class.java)

    suspend fun loginWithPhoneNumber(initialCredential: InitialCredential): Response<Login>?{

        return try {
            apiInterface.loginWithPhoneNumber(initialCredential)        }
        catch (e: Exception) {
           null
        }

    }


    suspend fun loginWithOtp(credentials: Credentials): Response<Token>?{
        return try {
            apiInterface.loginWithOtp(credentials)     }
        catch (e: Exception) {
            null
        }
    }


     fun getProfileDetails(token: String): Response<JSONObject>?{
        return try {
            apiInterface.getProfileDetails(token)       }
        catch (e: Exception) {
            Log.e("api call", "getProfileDetails: "+e.localizedMessage)
            null
        }
    }

}