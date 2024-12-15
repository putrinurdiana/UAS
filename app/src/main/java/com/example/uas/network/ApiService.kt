package com.example.uas.network

import com.example.uas.model.Vendors
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("Gvnmf/books")
    fun getAllResult() : Call<List<Vendors>>
}