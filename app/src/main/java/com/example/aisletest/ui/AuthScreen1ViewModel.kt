package com.example.aisletest.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.aisletest.model.InitialCredential
import com.example.aisletest.model.Login
import com.example.aisletest.model.Profile_list
import com.example.aisletest.repository.Repository
import kotlinx.coroutines.launch
import retrofit2.Response

class AuthScreen1ViewModel : ViewModel() {

    private val repository = Repository()

    fun loginWithPhoneNumber(initialCredential: InitialCredential): LiveData<Response<Login>>{

        val loginLiveData = MutableLiveData<Response<Login>>()
        viewModelScope.launch {
           val login = repository.loginWithPhoneNumber(initialCredential)
            loginLiveData.value = login!!
        }

        return loginLiveData

    }




}