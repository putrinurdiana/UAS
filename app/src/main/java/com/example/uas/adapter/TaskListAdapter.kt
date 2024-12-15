package com.example.uas.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.uas.database.TaskList
import com.example.uas.databinding.DataBinding

class TaskListAdapter(
    private var taskList: MutableList<TaskList>,
    private val onEditClick: (TaskList) -> Unit,
    private val onCompleteClick: (TaskList) -> Unit
) : RecyclerView.Adapter<TaskListAdapter.TaskViewHolder>() {

    // ViewHolder yang menangani binding
    inner class TaskViewHolder(private val binding: DataBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(task: TaskList) {
            // Menggunakan binding untuk menyetel data
            binding.tvTitle.text = task.title
            binding.tvDescription.text = task.description
            binding.tvDate.text = task.toString() // Pastikan Anda menampilkan tanggal sesuai keinginan

            // Mengatur listener untuk tombol edit dan complete
            binding.iconEdit.setOnClickListener {
                onEditClick(task) // Panggil fungsi untuk edit task
            }
            binding.iconComplete.setOnClickListener {
                onCompleteClick(task) // Panggil fungsi untuk mark task as complete
            }
        }
    }

    // Membuat ViewHolder baru
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val binding = DataBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TaskViewHolder(binding)
    }

    // Mengikat data dengan ViewHolder
    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        val task = taskList[position]
        holder.bind(task)
    }

    // Mengembalikan jumlah item dalam daftar
    override fun getItemCount(): Int = taskList.size

    // Fungsi untuk memperbarui daftar task di adapter
    fun updateTasks(tasks: List<TaskList>) {
        taskList.clear() // Hapus data lama
        taskList.addAll(tasks) // Tambahkan data baru
        notifyDataSetChanged() // Memberitahukan RecyclerView bahwa data telah berubah
    }
}
