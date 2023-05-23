package com.example.aisletest.ui.Notes

import android.content.Context
import android.net.ConnectivityManager
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.aisletest.databinding.FragmentNotesBinding


class NotesFragment : Fragment() {

    private var _binding: FragmentNotesBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val notesViewModel =
            ViewModelProvider(this).get(NotesViewModel::class.java)

        _binding = FragmentNotesBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val token  = "32c7794d2e6a1f7316ef35aa1eb34541"

        notesViewModel.getProfileDetails(token).observe(requireActivity() , Observer {

//            val profileList = it?.body()
//            val invites= profileList?.invites
//            val likes = profileList?.likes
//            val profile = invites?.profiles
//            val name = profile?.general_information?.first_name
//            val age = profile?.general_information?.age
//            val photos = profile?.photos
//            val photo = photos?.get(0)
//            val like_profiles = likes?.profiles

            // like_profiles.get()

         //   Log.i("likes_received_count", "likes_received_count: "+likes?.likes_received_count)
        })




        return root
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}