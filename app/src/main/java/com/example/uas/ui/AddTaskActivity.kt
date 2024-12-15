package com.example.uas.ui

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import com.example.uas.databinding.ActivityAddTaskBinding

class AddTaskActivity : AppCompatActivity() {

    // Deklarasi Binding
    private lateinit var binding: ActivityAddTaskBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inisialisasi Binding
        binding = ActivityAddTaskBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Fungsi tombol back menggunakan binding
        binding.btnBack.setOnClickListener {
            saveTaskAndReturn()
        }

        // Mengatur callback tombol back
        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                saveTaskAndReturn()
            }
        })
    }

    // Fungsi untuk menyimpan data task dan kembali ke Home
    private fun saveTaskAndReturn() {
        val title = binding.judul.text.toString().trim() // Ambil data judul
        val description = binding.etTaskDescription.text.toString().trim() // Ambil data deskripsi

        // Periksa apakah input kosong
        if (title.isNotEmpty() || description.isNotEmpty()) {
            val resultIntent = Intent()
            resultIntent.putExtra("TASK_TITLE", title)
            resultIntent.putExtra("TASK_DESCRIPTION", description)
            setResult(Activity.RESULT_OK, resultIntent) // Kirim hasil ke Activity sebelumnya
        } else {
            setResult(Activity.RESULT_CANCELED) // Jika tidak ada input, batalkan
        }

        finish() // Tutup activity dan kembali
    }
}
