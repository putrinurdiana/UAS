package com.example.uas

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.uas.database.TaskList
import com.example.uas.database.TaskListDatabase
import com.example.uas.database.ListDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TaskViewModel(application: Application) : AndroidViewModel(application) {

    // Akses ke ListDao untuk berinteraksi dengan database
    private val listDao: ListDao = TaskListDatabase.getDatabase(application).listDao()
    val allTasks: LiveData<List<TaskList>> = listDao.allTasks

    // Fungsi untuk menambahkan task
    fun insert(task: TaskList) = viewModelScope.launch(Dispatchers.IO) {
        listDao.insert(task)
    }

    // Fungsi untuk memperbarui task
    fun updateTask(task: TaskList) = viewModelScope.launch(Dispatchers.IO) {
        listDao.update(task)
    }

    // Fungsi untuk menghapus task
    fun deleteTask(task: TaskList) = viewModelScope.launch(Dispatchers.IO) {
        listDao.delete(task)
    }

    // Fungsi untuk menandai task sebagai selesai
    fun markTaskAsCompleted(task: TaskList) = viewModelScope.launch(Dispatchers.IO) {
        listDao.markAsCompleted(task.id)
    }

    // Fungsi untuk menandai task sebagai belum selesai
    fun markTaskAsNotCompleted(task: TaskList) = viewModelScope.launch(Dispatchers.IO) {
        listDao.markAsNotCompleted(task.id)
    }
}
