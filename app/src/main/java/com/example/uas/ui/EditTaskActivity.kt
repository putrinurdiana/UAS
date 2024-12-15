package com.example.uas.ui

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import com.example.uas.databinding.ActivityEditTaskBinding

class EditTaskActivity : AppCompatActivity() {

    // Deklarasi Binding
    private lateinit var binding: ActivityEditTaskBinding

    // Variabel untuk menampung data yang akan diedit
    private var taskId: Int = -1
    private var taskTitle: String? = null
    private var taskDescription: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inisialisasi Binding
        binding = ActivityEditTaskBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Ambil data yang dikirim dari HomeFragment
        taskId = intent.getIntExtra("TASK_ID", -1)
        taskTitle = intent.getStringExtra("TASK_TITLE")
        taskDescription = intent.getStringExtra("TASK_DESCRIPTION")

        // Pastikan data yang diterima valid sebelum lanjut
        if (taskId == -1 || taskTitle == null || taskDescription == null) {
            // Jika data tidak valid, tutup activity dan beri pesan kesalahan
            Toast.makeText(this, "Data task tidak valid", Toast.LENGTH_SHORT).show()
            finish()  // Tutup activity jika data tidak valid
            return
        }

        // Set data ke dalam input fields
        binding.judul.setText(taskTitle)
        binding.etTaskDescription.setText(taskDescription)

        // Fungsi tombol back menggunakan binding
        binding.btnBack.setOnClickListener {
            saveEditedTaskAndReturn()
        }

        // Handle tombol back pada perangkat
        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                saveEditedTaskAndReturn()
            }
        })
    }

    // Fungsi untuk menyimpan data yang sudah diedit dan kembali ke Home
    private fun saveEditedTaskAndReturn() {
        val updatedTitle = binding.judul.text.toString().trim() // Ambil judul yang diedit
        val updatedDescription = binding.etTaskDescription.text.toString().trim() // Ambil deskripsi yang diedit

        // Validasi input sebelum dikirim kembali
        if (updatedTitle.isEmpty() || updatedDescription.isEmpty()) {
            Toast.makeText(this, "Judul dan deskripsi tidak boleh kosong", Toast.LENGTH_SHORT).show()
            return  // Jangan lanjutkan jika ada input yang kosong
        }

        // Buat intent untuk mengirim data kembali
        val resultIntent = Intent()
        resultIntent.putExtra("TASK_ID", taskId)  // Kirim ID task kembali
        resultIntent.putExtra("TASK_TITLE", updatedTitle)
        resultIntent.putExtra("TASK_DESCRIPTION", updatedDescription)
        setResult(Activity.RESULT_OK, resultIntent) // Kirim hasil ke Activity sebelumnya

        finish() // Tutup activity
    }
}
