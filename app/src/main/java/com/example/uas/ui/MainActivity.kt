package com.example.uas.ui

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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Menginflate layout ActivityMainBinding
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Menemukan NavHostFragment yang telah didefinisikan di layout
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        // Mendapatkan NavController dari NavHostFragment
        navController = navHostFragment.navController

        // Menghubungkan BottomNavigationView dengan NavController untuk navigasi antar fragment
        val bottomNavView: BottomNavigationView = binding.bottomNavigationView
        // Setup BottomNavigationView dengan NavController
        NavigationUI.setupWithNavController(bottomNavView, navController)
    }
}
