package com.example.aisletest.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.aisletest.Util.NetworkUtils
import com.example.aisletest.databinding.ActivityAuthScreen2Binding
import com.example.aisletest.model.Credentials

class AuthScreen2Activity : AppCompatActivity() {

    private lateinit var authScreen2ViewModel: AuthScreen2ViewModel
    private lateinit var binding: ActivityAuthScreen2Binding
    private  var countDownTimer: CountDownTimer? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAuthScreen2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        authScreen2ViewModel= ViewModelProvider(this).get(AuthScreen2ViewModel::class.java)

        val phone_number = intent.getStringExtra("phone_number").toString()
        binding.phoneNumber.text = phone_number
        setTimer()

        binding.continueButton.setOnClickListener(View.OnClickListener {
            validateCredentials(phone_number)
        })

        binding.phoneNumber.setOnClickListener(View.OnClickListener {
            startActivity(Intent(this,AuthScreen1Activity::class.java))
            finish()
        })






    }

    private fun validateCredentials(phone_number: String) {

        if (NetworkUtils.isNetworkAvailable(this)) {
            val otp = binding.otp.text.toString()

            if (!otp.isBlank()) {
                val credentials = Credentials(phone_number, otp)

                binding.progressBar.visibility = View.VISIBLE
                binding.continueButton.isClickable = false
                authScreen2ViewModel.loginWithOtp(credentials).observe(this, Observer {
                    val token = it?.body()

                    binding.progressBar.visibility = View.GONE
                    binding.continueButton.isClickable = true

                    if (token?.token?.isBlank() == false) {

                        val intent = Intent(this, MainActivity::class.java)
                        intent.putExtra("token", token.token.toString())
                        startActivity(intent)
                        finish()

                    }
                    else{
                        Toast.makeText(this,"Invalid Credentials", Toast.LENGTH_LONG).show()
                    }
                })
            }
            else{
                Toast.makeText(this,"Please enter the OTP", Toast.LENGTH_LONG).show()
            }
        } else {
            Toast.makeText(this,"No Internet Connection", Toast.LENGTH_LONG).show()

        }


    }

    private fun setTimer() {
        val totalMillis = 60000L // Total duration of the timer in milliseconds
        val intervalMillis = 1000L // Interval for timer updates in milliseconds

        if (countDownTimer == null) {
            countDownTimer = object : CountDownTimer(totalMillis, intervalMillis) {
                override fun onTick(millisUntilFinished: Long) {
                    val seconds = millisUntilFinished / 1000
                    binding.timer.text = "00:$seconds"
                }

                override fun onFinish() {
                    binding.timer.text = "Timer finished"
                    countDownTimer = null
                }
            }

            countDownTimer?.start()
        }
    }


    override fun onDestroy() {
        super.onDestroy()

        countDownTimer?.cancel()
        countDownTimer = null
    }
}