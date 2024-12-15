package com.example.uas.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.uas.database.TaskList
import com.example.uas.databinding.DataBinding

class TaskListAdapter(
    private var taskList: MutableList<TaskList>, // Gunakan MutableList agar data dapat diperbarui
    private val onEditClick: (TaskList) -> Unit, // Callback untuk edit task
    private val onCompleteClick: (TaskList) -> Unit, // Callback untuk menandai task selesai
    private val onAddTaskClick: () -> Unit // Callback untuk menambahkan task baru
) : RecyclerView.Adapter<TaskListAdapter.TaskViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val binding = DataBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TaskViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        val task = taskList[position]
        val isLastItem = position == taskList.size - 1
        holder.bind(task, isLastItem, onEditClick, onCompleteClick, onAddTaskClick)
    }

    override fun getItemCount(): Int = taskList.size

    // Fungsi untuk memperbarui daftar task
    fun updateTasks(newTasks: List<TaskList>) {
        taskList.clear()
        taskList.addAll(newTasks)
        notifyDataSetChanged()
    }

    inner class TaskViewHolder(private val binding: DataBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(
            task: TaskList,
            isLastItem: Boolean,
            onEditClick: (TaskList) -> Unit,
            onCompleteClick: (TaskList) -> Unit,
            onAddTaskClick: () -> Unit
        ) {
            // Set data ke view
            binding.tvTitle.text = task.title
            binding.tvDate.text = task.createdAt
            binding.tvDescription.text = task.description

            // Tampilkan ikon edit dan handle klik
            binding.iconEdit.visibility = View.VISIBLE
            binding.iconEdit.setOnClickListener { onEditClick(task) }

            // Tampilkan ikon selesai dan handle klik
            binding.iconComplete.visibility = View.VISIBLE
            binding.iconComplete.setOnClickListener { onCompleteClick(task) }

            // Atur FAB hanya muncul di item terakhir
            if (isLastItem) {
                binding.fabAddTask.visibility = View.VISIBLE
                binding.fabAddTask.setOnClickListener { onAddTaskClick() }
            } else {
                binding.fabAddTask.visibility = View.GONE
            }
        }
    }
}
