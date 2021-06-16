package com.example.filmlist.data.models

import com.example.filmlist.core.domain.models.Film
import com.example.filmlist.core.domain.models.FilmDetailed

data class FilmLocal(
    val id: String,
    val poster: String,
    val title: String,
    val year: String,
    var liked: Boolean,
    val description: String? = null,
)

data class FilmsRemoteRequest(
    val apiKey: String,
)

data class FilmDetailedRemoteRequest(
    val apiKey: String,
    val id: String,
)

data class FilmRemoteResponse(
    val id: String,
    val name: String,
    val poster: String,
    val releaseYear: String,
)

data class FilmDetailedRemoteResponse(
    val id: String,
    val name: String,
    val poster: String,
    val releaseYear: String,
    val description: String?,
)

fun Film.toFilmLocal() = FilmLocal(
    id = id,
    poster = poster,
    title = title,
    year = year,
    liked = liked,
)

fun FilmDetailed.toFilmLocal() = FilmLocal(
    id = id,
    poster = poster,
    title = title,
    year = year,
    liked = liked,
    description = description
)

fun FilmLocal.toFilm() = Film(
    id = id,
    poster = poster,
    title = title,
    year = year,
    liked = liked,
)

fun FilmLocal.toFilmDetailed() = FilmDetailed(
    id = id,
    poster = poster,
    title = title,
    year = year,
    liked = liked,
    description = description
)

fun FilmRemoteResponse.toFilm() = Film(
    id = id,
    poster = poster,
    title = name,
    year = releaseYear,
)

fun FilmDetailedRemoteResponse.toFilmDetailed() = FilmDetailed(
    id = id,
    poster = poster,
    title = name,
    year = releaseYear,
    description = description,
)
