package com.example.filmlist.data.models

import com.example.filmlist.BuildConfig
import com.example.filmlist.core.domain.models.Film
import com.example.filmlist.core.domain.models.FilmDetailed
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

data class FilmLocal(
    val id: Int,
    val poster: String?,
    val title: String,
    val date: String,
    var liked: Boolean,
    val overview: String? = null,
    val voteAverage: Double? = null,
)

@Serializable
data class FilmsResponse(
    val page: Int,
    val results: List<FilmRemoteResponse>,
)

@Serializable
data class FilmRemoteResponse(
    val id: Int,
    val title: String = "",
    @SerialName(value = "poster_path")
    val poster: String?,
    @SerialName(value = "release_date")
    val releaseDate: String = "",
)

@Serializable
data class FilmDetailedRemoteResponse(
    val id: Int,
    val title: String = "",
    @SerialName(value = "poster_path")
    val poster: String?,
    @SerialName(value = "release_date")
    val releaseDate: String = "",
    val overview: String = "",
    @SerialName(value = "vote_average")
    val voteAverage: Double = 0.0,
)

fun Film.toFilmLocal() = FilmLocal(
    id = id,
    poster = poster,
    title = title,
    date = date,
    liked = liked,
)

fun FilmDetailed.toFilmLocal() = FilmLocal(
    id = id,
    poster = poster,
    title = title,
    date = date,
    liked = liked,
    overview = overview,
    voteAverage = voteAverage,
)

fun FilmLocal.toFilm() = Film(
    id = id,
    poster = poster,
    title = title,
    date = date,
    liked = liked,
)

fun FilmLocal.toFilmDetailed() = FilmDetailed(
    id = id,
    poster = poster,
    title = title,
    date = date,
    liked = liked,
    overview = overview,
    voteAverage = voteAverage,
)

fun FilmRemoteResponse.toFilm() = Film(
    id = id,
    poster = BuildConfig.IMG_BASE_URL_SMALL + poster,
    title = title,
    date = releaseDate,
)

fun FilmDetailedRemoteResponse.toFilmDetailed() = FilmDetailed(
    id = id,
    poster = BuildConfig.IMG_BASE_URL_LARGE + poster,
    title = title,
    date = releaseDate,
    overview = overview,
    voteAverage = voteAverage,
)
