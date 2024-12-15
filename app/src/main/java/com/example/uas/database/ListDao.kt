package com.example.uas.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import okhttp3.internal.concurrent.Task

@Dao
interface ListDao {
    // Menyisipkan data, dengan menggunakan strategi IGNORE jika terjadi konflik
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(list: TaskList)
    // Memperbarui data
    @Update
    fun update(note: TaskList)

    // Menghapus data
    @Delete
    fun delete(note: TaskList)

    // Mengambil semua tugas, dan menggunakan LiveData untuk mengamati perubahan data
    @get:Query("SELECT * FROM task_list ORDER BY id ASC")
    val allTasks: LiveData<List<TaskList>>
}

