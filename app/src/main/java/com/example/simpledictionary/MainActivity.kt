package com.example.simpledictionary

import android.os.Bundle
import android.preference.PreferenceManager
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.simpledictionary.databinding.MainActivityBinding
import com.example.simpledictionary.util.prefs.UserPrefs
import com.example.simpledictionary.util.prefs.defaultPrefsModule

class MainActivity : AppCompatActivity() {

    lateinit var navController: NavController
    private lateinit var binding: MainActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.Theme_SimpleDictionary)

        binding = MainActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        navController = Navigation.findNavController(this, R.id.nav_host_fragment)
    }
}





/*
        val graphInflater = navController.navInflater
        val navGraph = graphInflater.inflate(R.navigation.navigation_graph)

        val userPrefs = UserPrefs(PreferenceManager.getDefaultSharedPreferences(this))
        if(userPrefs.getAccessToken() == null) {
            navGraph.startDestination = R.id.loginFragment
        } else {
            navGraph.startDestination = R.id.mainFragment
        }

        navController = Navigation.findNavController(this, R.id.nav_host_fragment)
        navController.graph = navGraph
 */

