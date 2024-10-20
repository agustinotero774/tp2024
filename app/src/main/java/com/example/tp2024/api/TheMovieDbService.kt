package com.example.tp2024.api

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface TheMovieDbService {

    @GET("discover/movie?sort_by=popularity.desc")
    fun listaPeliculasPopulares(@Query("api_key")apiKey: String): Call<MovieDbResult>

}