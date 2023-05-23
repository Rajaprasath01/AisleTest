package com.example.aisletest.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.aisletest.R
import com.example.aisletest.Util.NetworkUtils
import com.example.aisletest.databinding.ActivityAuthScreen1Binding
import com.example.aisletest.model.InitialCredential
import com.example.aisletest.repository.Repository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AuthScreen1Activity : AppCompatActivity() {

    private lateinit var authScreen1ViewModel : AuthScreen1ViewModel
    private lateinit var binding: ActivityAuthScreen1Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding= ActivityAuthScreen1Binding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.continueButton.setOnClickListener(View.OnClickListener {
            validate_phone_number()
        })
        authScreen1ViewModel = ViewModelProvider(this).get(AuthScreen1ViewModel::class.java)





    }

    private fun validate_phone_number() {

        if (NetworkUtils.isNetworkAvailable(this)) {
            val phone_number =binding.countryCode.text.toString() + binding.phoneNumber.text.toString()
            val initialCredential = InitialCredential(phone_number)

            authScreen1ViewModel.loginWithPhoneNumber(initialCredential).observe(this, Observer {

                var status = it.body()?.status

                if (status == true){
                    val intent = Intent(this,AuthScreen2Activity::class.java)
                    intent.putExtra("phone_number",phone_number)
                    startActivity(intent)
                    finish()
                }
                else{
                    Toast.makeText(this,"Invalid number",Toast.LENGTH_LONG).show()
                }
            })
        } else {

            Toast.makeText(this,"No Internet Connection",Toast.LENGTH_LONG).show()
        }

    }
}