package com.example.aisletest.ui.Notes

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.aisletest.model.Profile_list
import com.example.aisletest.model.Token
import com.example.aisletest.repository.Repository
import kotlinx.coroutines.launch
import retrofit2.Response

class NotesViewModel : ViewModel() {

    private val repository = Repository()

     fun getProfileDetails(token: String): MutableLiveData<Response<Profile_list>?> {

         val profileListLiveData = MutableLiveData<Response<Profile_list>?>()
         viewModelScope.launch {
             Log.i("token", "getProfileDetails: "+token)
           val profileList = repository.getProfileDetails(token)
             profileListLiveData.value = profileList
         }
       return profileListLiveData
    }
}