package com.example.filmlist.core.domain.models

data class Film(
    val id: Int,
    val poster: String?,
    val title: String,
    val date: String,
    val liked: Boolean = false,
)
