package com.example.uas.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import okhttp3.internal.concurrent.Task

@Database(entities = [TaskList::class], version = 1, exportSchema = false)
abstract class TaskListDatabase : RoomDatabase() {

    abstract fun listDao(): ListDao

    companion object {
        @Volatile
        private var INSTANCE: TaskListDatabase? = null

        fun getDatabase(context: Context): TaskListDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    TaskListDatabase::class.java,
                    "task_list"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
