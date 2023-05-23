package com.example.aisletest.ui

import android.content.Intent
import android.opengl.Visibility
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
            validatePhoneNumber()
        })
        authScreen1ViewModel = ViewModelProvider(this).get(AuthScreen1ViewModel::class.java)





    }

    private fun validatePhoneNumber() {

        if (NetworkUtils.isNetworkAvailable(this)) {
            val phone_number =binding.countryCode.text.toString() + binding.phoneNumber.text.toString()
            if (!phone_number.isBlank()) {
                val initialCredential = InitialCredential(phone_number)
                binding.progressBar.visibility = View.VISIBLE
                binding.continueButton.isClickable = false

                authScreen1ViewModel.loginWithPhoneNumber(initialCredential)
                    .observe(this, Observer {

                        val status = it.body()?.status

                        binding.progressBar.visibility = View.GONE
                        binding.continueButton.isClickable = true


                        if (status == true) {
                            val intent = Intent(this, AuthScreen2Activity::class.java)
                            intent.putExtra("phone_number", phone_number)
                            startActivity(intent)
                            finish()
                        } else {
                            Toast.makeText(this, "Invalid number", Toast.LENGTH_LONG).show()
                        }
                    })
            }
            else{
                Toast.makeText(this,"Please enter a mobile number",Toast.LENGTH_LONG).show()
            }
        } else {

            Toast.makeText(this,"No Internet Connection",Toast.LENGTH_LONG).show()
        }

    }
}