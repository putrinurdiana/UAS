package com.example.uas.ui

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.uas.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var sharedPreferences: SharedPreferences

    companion object {
        const val Email = "admin@example.com"
        const val Password = "password123"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initialize SharedPreferences
        sharedPreferences = getSharedPreferences("user_prefs", MODE_PRIVATE)

        // Check if user is already logged in
        val isLoggedIn = sharedPreferences.getBoolean("isLoggedIn", false)
        if (isLoggedIn) {
            navigateToMainActivity()
            return
        }

        // Handle login button click
        binding.btnLogin.setOnClickListener {
            val email = binding.teksEmail.text.toString()
            val password = binding.teksPassword.text.toString()

            // Validate empty input
            if (email.isEmpty() || password.isEmpty()) {
                showToast("Email dan password tidak boleh kosong")
                return@setOnClickListener
            }

            // Validate login
            if (email == Email && password == Password) {
                saveLoginStatus(email, password) // Save login data
                navigateToMainActivity()
            } else {
                // If login fails, show error message
                showToast("Email atau password salah")
            }
        }
    }

    // Navigate to MainActivity
    private fun navigateToMainActivity() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    // Save login status and credentials
    private fun saveLoginStatus(email: String, password: String) {
        with(sharedPreferences.edit()) {
            putBoolean("isLoggedIn", true) // Save login status
            putString("userEmail", email) // Save email
            putString("userPassword", password) // Save password
            apply()
        }
    }

    // Show toast message
    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}
