package com.example.uas.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.uas.TaskViewModel
import com.example.uas.adapter.TaskAdapter
import com.example.uas.databinding.FragmentTaskBinding
import com.example.uas.database.TaskList
import com.google.android.material.snackbar.Snackbar

class TaskFragment : Fragment() {

    private lateinit var binding: FragmentTaskBinding
    private lateinit var taskViewModel: TaskViewModel
    private lateinit var taskAdapter: TaskAdapter  // Use TaskAdapter instead of TaskListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentTaskBinding.inflate(inflater, container, false)

        // Inisialisasi ViewModel
        taskViewModel = ViewModelProvider(this).get(TaskViewModel::class.java)

        // Setup RecyclerView
        setupRecyclerView()

        // Observasi data dari database dan perbarui RecyclerView
        taskViewModel.allTasks.observe(viewLifecycleOwner) { tasks ->
            tasks?.let {
                taskAdapter.updateTasks(it)  // Update the tasks in the adapter
            }
        }

        return binding.root
    }

    private fun setupRecyclerView() {
        // Inisialisasi adapter dengan callback hanya untuk delete
        taskAdapter = TaskAdapter(
            taskList = mutableListOf(),
            onDeleteClick = { task ->  // Callback for delete
                deleteTask(task)
            }
        )

        // Set RecyclerView dengan layout manager dan adapter
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = taskAdapter
        }
    }

    // Fungsi untuk menghapus task
    private fun deleteTask(task: TaskList) {
        // Menghapus task dari database
        taskViewModel.deleteTask(task)

        // Menampilkan Snackbar untuk konfirmasi penghapusan
        Snackbar.make(binding.root, "Task Deleted", Snackbar.LENGTH_SHORT).show()
    }
}
