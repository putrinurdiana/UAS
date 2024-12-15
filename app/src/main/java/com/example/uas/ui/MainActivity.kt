package com.example.uas.ui

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
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

        // Menginflate layout ActivityMainBinding
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Inisialisasi SharedPreferences
        sharedPreferences = getSharedPreferences("user_prefs", MODE_PRIVATE)

        // Cek status login pengguna
        val isLoggedIn = sharedPreferences.getBoolean("isLoggedIn", false)

        // Jika pengguna belum login, arahkan ke LoginActivity
        if (!isLoggedIn) {
            navigateToLoginActivity()
            return // Menghentikan eksekusi kode MainActivity lebih lanjut
        }

        // Menemukan NavHostFragment yang telah didefinisikan di layout
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController

        // Menghubungkan BottomNavigationView dengan NavController untuk navigasi antar fragment
        val bottomNavView: BottomNavigationView = binding.bottomNavigationView
        NavigationUI.setupWithNavController(bottomNavView, navController)

        // Memastikan BottomNavigationView muncul saat aplikasi dijalankan
        bottomNavView.visibility = View.VISIBLE

        // Pastikan HomeFragment ditampilkan saat aplikasi dibuka
        if (savedInstanceState == null) {
            // Menavigasi ke HomeFragment jika pertama kali membuka MainActivity
            navController.navigate(R.id.homeFragment)
        }
    }

    // Fungsi untuk menavigasi ke LoginActivity
    private fun navigateToLoginActivity() {
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        finish() // Menutup MainActivity agar tidak bisa kembali ke MainActivity tanpa login
    }

    // Fungsi untuk menangani login yang berhasil
    fun onLoginSuccess() {
        // Simpan status login ke SharedPreferences
        with(sharedPreferences.edit()) {
            putBoolean("isLoggedIn", true)
            apply()
        }

        // Menampilkan BottomNavigationView setelah login sukses
        binding.bottomNavigationView.visibility = View.VISIBLE

        // Navigasi ke HomeFragment
        navController.navigate(R.id.homeFragment)
    }

    // Fungsi untuk logout pengguna
    fun logout() {
        // Hapus status login dari SharedPreferences
        with(sharedPreferences.edit()) {
            putBoolean("isLoggedIn", false)
            apply()
        }

        // Navigasi ke LoginActivity setelah logout
        navigateToLoginActivity()

        // Sembunyikan BottomNavigationView setelah logout
        binding.bottomNavigationView.visibility = View.GONE
    }
}
