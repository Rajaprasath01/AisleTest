package com.example.aisletest.ui.Notes

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.aisletest.GlideLibrary.BlurTransformation
import com.example.aisletest.Util.NetworkUtils
import com.example.aisletest.databinding.FragmentNotesBinding
import com.example.aisletest.ui.MainActivity


class NotesFragment : Fragment() {

    private var _binding: FragmentNotesBinding? = null
    private lateinit var notesViewModel : NotesViewModel

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
         notesViewModel =
            ViewModelProvider(this).get(NotesViewModel::class.java)

        _binding = FragmentNotesBinding.inflate(inflater, container, false)
        val root: View = binding.root

        (requireActivity() as AppCompatActivity).supportActionBar?.hide()



        val token  = (requireActivity() as MainActivity).token

        Log.i("notes_fragment", "onCreateView: "+token)

        if (NetworkUtils.isNetworkAvailable(requireActivity())) {
            setViewData(token)
        } else {
            Toast.makeText(requireActivity(),"No Internet Connection",Toast.LENGTH_LONG).show()
        }





        return root
    }

    private fun setViewData(token: String) {
        notesViewModel.getProfileDetails(token).observe(requireActivity() , Observer {

            val profileList = it?.body()
            val invites= profileList?.invites
            val likes = profileList?.likes
            val profile = invites?.profiles
            val name = profile?.get(0)?.general_information?.first_name
            val age = profile?.get(0)?.general_information?.age
            val photos = profile?.get(0)?.photos
            val photo = photos?.get(0)
            val like_profiles = likes?.profiles



            Glide.with(requireActivity()).load(photo?.photo).into(binding.matchPic)
            binding.matchName.text = "$name $age"

            Glide.with(requireActivity()).load(like_profiles?.get(0)?.avatar).transform(BlurTransformation(requireActivity())).into(binding.profile1pic)
            binding.profile1name.text = like_profiles?.get(0)?.first_name

            Glide.with(requireActivity()).load(like_profiles?.get(1)?.avatar).transform(BlurTransformation(requireActivity())).into(binding.profile2pic)
            binding.profile2name.text = like_profiles?.get(1)?.first_name


            Log.i("likes_received_count", "likes_received_count: $name $age")
        })


    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}