package com.example.filmlist.ui.films

import android.net.Uri
import com.example.filmlist.core.domain.models.Film

data class FilmRecyclerItem(
    val id: String,
    val poster: Uri?,
    val title: String,
    val year: String,
    val liked: Boolean,
)

fun Film.toFilmRecyclerItem() = FilmRecyclerItem(
    id = id,
    poster = null,
    title = title,
    year = year,
    liked =
)
