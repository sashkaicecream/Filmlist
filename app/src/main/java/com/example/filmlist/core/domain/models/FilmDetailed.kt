package com.example.filmlist.core.domain.models

data class FilmDetailed(
    val id: String,
    val poster: String,
    val title: String,
    val year: String,
    val liked: Boolean = false,
    val description: String?,
)
