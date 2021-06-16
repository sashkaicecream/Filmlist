package com.example.filmlist.core.domain.models

data class Film(
    val id: String,
    val poster: String,
    val title: String,
    val year: String,
    val liked: Boolean = false,
)
