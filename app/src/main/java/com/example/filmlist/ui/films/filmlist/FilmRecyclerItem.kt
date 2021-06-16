package com.example.filmlist.ui.films.filmlist

import android.net.Uri
import com.example.filmlist.core.domain.models.Film

data class FilmRecyclerItem(
    val id: Int,
    val poster: Uri?,
    val title: String,
    val date: String,
    var liked: Boolean,
)

fun Film.toFilmRecyclerItem() = FilmRecyclerItem(
    id = id,
    poster = Uri.parse(poster),
    title = title,
    date = date,
    liked = liked
)
