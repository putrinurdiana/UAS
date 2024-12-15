package com.example.uas.ui

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.uas.TaskViewModel
import com.example.uas.adapter.TaskListAdapter
import com.example.uas.database.TaskList
import com.example.uas.databinding.FragmentHomeBinding
import com.google.android.material.floatingactionbutton.FloatingActionButton

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var taskViewModel: TaskViewModel
    private lateinit var taskListAdapter: TaskListAdapter

    // ActivityResultLauncher untuk menangkap data dari EditTaskActivity
    private val editTaskLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val data = result.data
                val taskId = data?.getIntExtra("TASK_ID", -1) ?: -1
                val updatedTitle = data?.getStringExtra("TASK_TITLE") ?: ""
                val updatedDescription = data?.getStringExtra("TASK_DESCRIPTION") ?: ""

                // Pastikan taskId valid sebelum melakukan update
                if (taskId != -1) {
                    val updatedTask = TaskList(
                        id = taskId,
                        title = updatedTitle,
                        description = updatedDescription,
                        isCompleted = false
                    )
                    taskViewModel.updateTask(updatedTask) // Update task di database
                } else {
                    // Jika taskId tidak valid, beri log atau feedback
                }
            }
        }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater, container, false)

        // Inisialisasi ViewModel
        taskViewModel = ViewModelProvider(this).get(TaskViewModel::class.java)

        // Setup RecyclerView
        setupRecyclerView()

        // Observasi data dari database dan perbarui RecyclerView
        taskViewModel.allTasks.observe(viewLifecycleOwner) { tasks ->
            tasks?.let {
                taskListAdapter.updateTasks(it)
            }
        }

        // Inisialisasi FloatingActionButton
        val fabAddTask: FloatingActionButton = binding.fabAddTask
        fabAddTask.setOnClickListener {
            openAddTaskPage()
        }

        return binding.root
    }

    private fun setupRecyclerView() {
        // Inisialisasi adapter dengan callback
        taskListAdapter = TaskListAdapter(
            taskList = mutableListOf(),
            onEditClick = { task ->
                editTask(task) // Handle edit task
            },
            onCompleteClick = { task ->
                markTaskAsComplete(task) // Handle mark task as complete
            }
        )

        // Set RecyclerView dengan layout manager dan adapter
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = taskListAdapter
        }
    }

    // Fungsi untuk mengedit task
    private fun editTask(task: TaskList) {
        val intent = Intent(requireContext(), EditTaskActivity::class.java).apply {
            putExtra("TASK_ID", task.id) // Kirim task ID
            putExtra("TASK_TITLE", task.title) // Kirim judul task
            putExtra("TASK_DESCRIPTION", task.description) // Kirim deskripsi task
        }
        editTaskLauncher.launch(intent) // Luncurkan activity dengan launcher
    }

    // Fungsi untuk menandai task sebagai selesai
    private fun markTaskAsComplete(task: TaskList) {
        val updatedTask = task.copy(isCompleted = true) // Tandai task sebagai selesai
        taskViewModel.updateTask(updatedTask) // Update task di database
    }

    // Fungsi untuk membuka halaman tambah task
    private fun openAddTaskPage() {
        val intent = Intent(requireContext(), AddTaskActivity::class.java)
        startActivity(intent)
    }
}
