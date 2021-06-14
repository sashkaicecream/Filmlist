package com.example.filmlist.ui.films

import android.net.Uri

data class FilmRecyclerItem(
    val poster: Uri?,
    val title: String,
    val year: String,
    val liked: Boolean,
)
