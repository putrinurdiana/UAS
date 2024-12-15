package com.example.uas

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.uas.database.TaskList
import com.example.uas.database.TaskListDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TaskViewModel(application: Application) : AndroidViewModel(application) {

    private val taskDao = TaskListDatabase.getDatabase(application).listDao()
    val allTasks: LiveData<List<TaskList>> = taskDao.allTasks

    // Fungsi untuk menambahkan task
    fun insertTask(task: TaskList) = viewModelScope.launch(Dispatchers.IO) {
        taskDao.insert(task)
    }

    // Fungsi untuk memperbarui task
    fun updateTask(task: TaskList) = viewModelScope.launch(Dispatchers.IO) {
        taskDao.update(task)
    }
}
