package com.example.uas.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.uas.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    // Variabel untuk menyimpan email dan password yang valid
    companion object {
        const val Email = "admin@example.com"
        const val Password = "password123"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Menemukan elemen-elemen UI
        val teksEmail: EditText = binding.teksEmail
        val teksPassword: EditText = binding.teksPassword
        val btnLogin: Button = binding.btnLogin
        val teksLogin: TextView = binding.teksLogin

        // Menangani tombol login
        btnLogin.setOnClickListener {
            val email = teksEmail.text.toString()
            val password = teksPassword.text.toString()

            // Validasi login
            if (email == Email && password == Password) {
                // Jika login berhasil, menuju ke MainActivity
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish() // Hapus LoginActivity dari back stack
            } else {
                // Jika login gagal, tampilkan pesan error
                btnLogin.text = "Email atau password salah"
            }
        }
    }
}
