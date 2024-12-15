package com.example.uas.ui

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.uas.ui.LoginFragment
import com.example.uas.databinding.FragmentProfileBinding

class ProfileFragment : Fragment() {

    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var binding: FragmentProfileBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Menggunakan View Binding
        binding = FragmentProfileBinding.inflate(inflater, container, false)

        // Inisialisasi SharedPreferences
        sharedPreferences = requireActivity().getSharedPreferences("UserPrefs", Context.MODE_PRIVATE)

        // Mengambil data dari SharedPreferences
        val username = sharedPreferences.getString("username", "User")
        val email = sharedPreferences.getString("email", "user@example.com")
        val password = sharedPreferences.getString("password", "defaultPassword")

        // Menampilkan data pada TextView menggunakan binding
        binding.tvName.text = "Nama : $username"
        binding.tvEmail.text = "Email : $email"
        binding.tvPassword.text = "Password : $password"

        // Menangani klik tombol logout
        binding.btnLogout.setOnClickListener {
            // Menghapus data login dari SharedPreferences
            val editor = sharedPreferences.edit()
            editor.clear()  // Menghapus semua data yang ada di SharedPreferences
            editor.apply()

            // Menavigasi ke LoginActivity setelah logout
            val intent = Intent(requireActivity(), LoginFragment::class.java)
            startActivity(intent)

            // Menutup aktivitas ProfileFragment agar tidak kembali ke fragment ini
            requireActivity().finish()
        }

        return binding.root
    }
}
