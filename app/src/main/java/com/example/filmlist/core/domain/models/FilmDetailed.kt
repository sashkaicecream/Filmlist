package com.example.filmlist.core.domain.models

data class FilmDetailed(
    val id: Int,
    val poster: String?,
    val title: String,
    val date: String,
    val liked: Boolean = false,
    val overview: String?,
    val voteAverage: Double?,
)
