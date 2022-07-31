package dev.maryann.myposts


import retrofit2.Call
import retrofit2.http.GET

interface ApiInterface {
    @GET("Posts")
    fun getPosts(): Call<List<Post>>
    }