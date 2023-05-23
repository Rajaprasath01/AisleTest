package com.example.aisletest.ui

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.LinearLayout
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.aisletest.R
import com.example.aisletest.databinding.ActivityMainBinding
import com.example.aisletest.model.Token
import com.example.aisletest.ui.Discover.DiscoverFragment
import com.example.aisletest.ui.Matches.MatchesFragment
import com.example.aisletest.ui.Notes.NotesFragment
import com.example.aisletest.ui.Profile.ProfileFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

   public lateinit var token: String



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

       token =intent.getStringExtra("token").toString()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView

        val color = ContextCompat.getColor(this, R.color.violet)
        val notesBadge = navView.getOrCreateBadge(R.id.navigation_notes)
        notesBadge.number = 9
        notesBadge.backgroundColor = color
        val matchesBadge = navView.getOrCreateBadge(R.id.navigation_matches)
        matchesBadge.number = 30
        matchesBadge.backgroundColor = color

        val navController = this.findNavController(R.id.fragmentContainerView)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_discover,
                R.id.navigation_notes,
                R.id.navigation_matches,
                R.id.navigation_profile
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }

}