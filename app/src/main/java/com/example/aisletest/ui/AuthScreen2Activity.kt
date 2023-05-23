package com.example.aisletest.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.aisletest.R
import com.example.aisletest.model.Credentials

class AuthScreen2Activity : AppCompatActivity() {

    private lateinit var authScreen2ViewModel: AuthScreen2ViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth_screen2)

        authScreen2ViewModel= ViewModelProvider(this).get(AuthScreen2ViewModel::class.java)

        val credentials = Credentials("+919876543212","1234")

        authScreen2ViewModel.loginWithOtp(credentials).observe(this, Observer {
          val token =  it.body()?.token
            Log.i("token", "token: "+token)
        })
    }
}