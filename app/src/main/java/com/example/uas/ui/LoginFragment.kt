package com.example.uas.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.uas.R
import com.example.uas.databinding.FragmentLoginBinding
import com.example.uas.ui.MainActivity

class LoginFragment : Fragment() {

    // Variabel untuk binding
    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Menggunakan View Binding untuk menghubungkan layout dengan kode
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        val view = binding.root

        // Mengakses elemen-elemen UI melalui binding
        val emailEditText = binding.teksEmail
        val passwordEditText = binding.teksPassword
        val btnLogin = binding.btnLogin

        btnLogin.setOnClickListener {
            var loading = false
            val email = emailEditText.text.toString()
            val password = passwordEditText.text.toString()

            // Validasi form input
            if (email.isEmpty()) {
                emailEditText.error = "Email tidak boleh kosong"
                loading = true
            }
            if (password.isEmpty()) {
                passwordEditText.error = "Password tidak boleh kosong"
                loading = true
            }

            // Cek email dan password yang valid
            if (!loading && email == MainActivity.Email && password == MainActivity.Password) {
                // Login berhasil
                Toast.makeText(requireContext(), "Login berhasil", Toast.LENGTH_SHORT).show()

                // Pindah ke HomeFragment setelah login berhasil
                findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
            } else {
                // Tampilkan pesan kesalahan jika login gagal
                Toast.makeText(requireContext(), "Email atau Password salah", Toast.LENGTH_SHORT).show()
            }
        }

        return view
    }

    // Pastikan untuk membersihkan binding setelah fragment dihancurkan
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
