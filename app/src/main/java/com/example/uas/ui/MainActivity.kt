package com.example.uas.ui

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.uas.R
import com.example.uas.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    // Menyimpan email dan password untuk login
    companion object {
        const val Email = "admin@example.com"
        const val Password = "password123"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Menginflate layout ActivityMainBinding
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Menampilkan LoginFragment pertama kali jika belum login
        if (savedInstanceState == null) {
            showLoginFragment()
        }
    }

    // Fungsi untuk menampilkan LoginFragment
    private fun showLoginFragment() {
        val loginFragment = LoginFragment()

        // Menggunakan FragmentTransaction untuk mengganti fragmen
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.nav_host_fragment, loginFragment)
        transaction.commit()

        // Sembunyikan BottomNavigationView setelah login
        binding.bottomNavigationView.visibility = View.GONE
    }

    // Fungsi untuk menangani login yang berhasil dan menampilkan HomeFragment
    fun onLoginSuccess() {
        // Menampilkan BottomNavigationView setelah login sukses
        binding.bottomNavigationView.visibility = View.VISIBLE

        // Navigasi ke HomeFragment menggunakan FragmentTransaction
        val homeFragment = HomeFragment()

        // Menggunakan FragmentTransaction untuk mengganti fragmen
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.nav_host_fragment, homeFragment)
        transaction.commit()
    }
}
