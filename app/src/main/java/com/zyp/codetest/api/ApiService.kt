package com.zyp.codetest.api

import com.zyp.codetest.model.User
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {

    @GET("/users")
    fun getUsers(): Call<List<User>>
}