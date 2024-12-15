package com.example.uas.ui

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.uas.databinding.FragmentProfileBinding

class ProfileFragment : Fragment() {

    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var binding: FragmentProfileBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Use View Binding
        binding = FragmentProfileBinding.inflate(inflater, container, false)

        // Initialize SharedPreferences
        sharedPreferences = requireActivity().getSharedPreferences("user_prefs", Context.MODE_PRIVATE)

        // Get login data
        val (email, password) = getLoginData()

        // Display data on TextViews using binding
        binding.tvEmail.text = "Email: $email"
        binding.tvPassword.text = "Password: $password"

        // Handle logout button click
        binding.btnLogout.setOnClickListener {
            logoutUser()
        }

        return binding.root
    }

    private fun getLoginData(): Pair<String, String> {
        val email = sharedPreferences.getString("userEmail", "default@example.com") ?: "default@example.com"
        val password = sharedPreferences.getString("userPassword", "password123") ?: "password123"
        return Pair(email, password)
    }

    private fun logoutUser() {
        // Clear login data from SharedPreferences
        with(sharedPreferences.edit()) {
            clear() // Clear all SharedPreferences data
            apply()
        }

        // Navigate to LoginActivity
        val intent = Intent(requireActivity(), LoginActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)

        // Close current activity
        requireActivity().finish()
    }
}
