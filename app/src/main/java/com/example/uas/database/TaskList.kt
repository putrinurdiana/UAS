package com.example.uas.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "task_list")
data class TaskList(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0, // ID lokal untuk Room (auto-generated)
    @ColumnInfo(name = "_id")
    val _id: String, // ID dari API
    @ColumnInfo(name = "title")
    val title: String,
    @ColumnInfo(name = "description")
    val description: String,
    @ColumnInfo(name = "isCompleted")
    val isCompleted: Boolean,
    @ColumnInfo(name = "createdAt")
    val createdAt: String
)