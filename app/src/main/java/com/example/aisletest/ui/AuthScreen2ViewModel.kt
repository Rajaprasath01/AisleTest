package com.example.aisletest.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.aisletest.model.Credentials
import com.example.aisletest.model.Login
import com.example.aisletest.model.Token
import com.example.aisletest.repository.Repository
import kotlinx.coroutines.launch
import retrofit2.Response

class AuthScreen2ViewModel : ViewModel() {

    private val repository = Repository()

    fun loginWithOtp(credentials: Credentials): LiveData<Response<Token>> {
        val tokenLiveData = MutableLiveData<Response<Token>>()
        viewModelScope.launch {
          var token = repository.loginWithOtp(credentials)
            tokenLiveData.value = token!!
        }

        return tokenLiveData
    }

}