package com.example.uas.model

import com.google.gson.annotations.SerializedName
import java.util.Date

data class Vendors(
    @SerializedName("_id")
    val id: String,

    @SerializedName("title")
    val title: String,

    @SerializedName("description")
    val description: String,

    @SerializedName("isCompleted")
    val isCompleted: Boolean,

    @SerializedName("createdAt")
    val createdAt: Date
)