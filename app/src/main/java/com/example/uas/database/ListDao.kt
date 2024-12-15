package com.example.uas.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface ListDao {
    // Menyisipkan data, dengan menggunakan strategi IGNORE jika terjadi konflik
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(list: TaskList)

    // Memperbarui data task
    @Update
    fun update(task: TaskList)

    // Menghapus data task
    @Delete
    fun delete(task: TaskList)

    // Mengambil semua task, dan menggunakan LiveData untuk mengamati perubahan data
    @get:Query("SELECT * FROM task_list ORDER BY id ASC")
    val allTasks: LiveData<List<TaskList>>

    // Menandai task sebagai selesai (isCompleted = 1)
    @Query("UPDATE task_list SET isCompleted = 1 WHERE id = :taskId")
    suspend fun markAsCompleted(taskId: Int)

    // Menandai task sebagai belum selesai (isCompleted = 0)
    @Query("UPDATE task_list SET isCompleted = 0 WHERE id = :taskId")
    suspend fun markAsNotCompleted(taskId: Int)

    // Mengambil task berdasarkan status apakah sudah selesai
    @Query("SELECT * FROM task_list WHERE isCompleted = :status ORDER BY id ASC")
    fun getTasksByStatus(status: Boolean): LiveData<List<TaskList>>
}
