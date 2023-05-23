package com.example.aisletest.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.aisletest.R
import com.example.aisletest.model.InitialCredential
import com.example.aisletest.repository.Repository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AuthScreen1Activity : AppCompatActivity() {

    private lateinit var authScreen1ViewModel : AuthScreen1ViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth_screen1)

        authScreen1ViewModel = ViewModelProvider(this).get(AuthScreen1ViewModel::class.java)
        val initialCredential = InitialCredential("+919876543212")
        authScreen1ViewModel.loginWithPhoneNumber(initialCredential).observe(this, Observer {

            var status = it.body()?.status

            Log.i("login_status", "onCreate: "+status)
        })




    }
}