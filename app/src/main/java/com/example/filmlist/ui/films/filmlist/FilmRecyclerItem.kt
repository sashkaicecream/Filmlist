package com.example.filmlist.ui.films.filmlist

import android.net.Uri
import com.example.filmlist.core.domain.models.Film

data class FilmRecyclerItem(
    val id: String,
    val poster: Uri?,
    val title: String,
    val year: String,
    var liked: Boolean,
)

fun Film.toFilmRecyclerItem() = FilmRecyclerItem(
    id = id,
    poster = null,
    title = title,
    year = year,
    liked = liked
)
