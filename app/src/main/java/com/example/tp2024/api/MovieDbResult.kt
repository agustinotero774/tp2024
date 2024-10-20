package com.example.tp2024.api

data class MovieDbResult(
    val page: Int,
    val results: List<Pelicula>,
    val total_pages: Int,
    val total_results: Int
)