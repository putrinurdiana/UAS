package com.example.uas.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.uas.database.TaskList
import com.example.uas.databinding.DataTaskBinding // Binding for item with delete only

class TaskAdapter(
    private var taskList: MutableList<TaskList>,
    private val onDeleteClick: (TaskList) -> Unit // Callback only for deleting task
) : RecyclerView.Adapter<TaskAdapter.TaskViewHolder>() {

    // ViewHolder for items with delete icon
    inner class TaskViewHolder(private val binding: DataTaskBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(task: TaskList) {
            // Set task data into the view
            binding.tvTitle.text = task.title
            binding.tvDate.text = task.toString() // Set date properly
            binding.tvDescription.text = task.description

            // Set the delete icon click listener
            binding.iconDelete.setOnClickListener {
                onDeleteClick(task) // Trigger delete callback
            }
        }
    }

    // Create new view holder for each task item
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val binding = DataTaskBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TaskViewHolder(binding)
    }

    // Bind task data to the view
    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        val task = taskList[position]
        holder.bind(task)
    }

    // Return the number of items in the list
    override fun getItemCount(): Int = taskList.size

    // Update the list of tasks
    fun updateTasks(tasks: List<TaskList>) {
        taskList.clear()
        taskList.addAll(tasks)
        notifyDataSetChanged()
    }
}
