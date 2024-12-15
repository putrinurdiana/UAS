package com.example.uas.ui

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.example.uas.R
import com.example.uas.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inflate layout using View Binding
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initialize SharedPreferences
        sharedPreferences = getSharedPreferences("user_prefs", MODE_PRIVATE)

        // Check login status
        val isLoggedIn = sharedPreferences.getBoolean("isLoggedIn", false)

        // If not logged in, navigate to LoginActivity
        if (!isLoggedIn) {
            navigateToLoginActivity()
            return // Stop further execution
        }

        // Set up Navigation Controller
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController

        // Set up Bottom Navigation with NavController
        val bottomNavView: BottomNavigationView = binding.bottomNavigationView
        NavigationUI.setupWithNavController(bottomNavView, navController)

        // Handle bottom navigation item selection, especially the Profile icon
        bottomNavView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.homeFragment -> {
                    navController.navigate(R.id.homeFragment) // Navigate to home fragment
                    true
                }
                R.id.profileFragment -> {
                    navController.navigate(R.id.profileFragment) // Navigate to profile fragment
                    true
                }
                else -> false
            }
        }
    }

    // Function to navigate to LoginActivity
    private fun navigateToLoginActivity() {
        val intent = Intent(this, LoginActivity::class.java)
        // Set flag to ensure user cannot go back to MainActivity after logout
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
        finish() // Close MainActivity to prevent back navigation
    }

    // Function to log out the user
    fun logout() {
        // Clear login status from SharedPreferences
        with(sharedPreferences.edit()) {
            putBoolean("isLoggedIn", false)
            apply()
        }

        // Navigate to LoginActivity after logout
        navigateToLoginActivity()
    }
}
