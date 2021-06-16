package com.example.filmlist.core.domain.repository

import com.example.filmlist.core.datasource.FilmsDataSource
import com.example.filmlist.core.domain.models.Film
import com.github.michaelbull.result.map
import com.github.michaelbull.result.onSuccess
import com.github.michaelbull.result.orElse

class FilmsRepository(
    private val local: FilmsDataSource,
    private val remote: FilmsDataSource,
) {
    suspend fun getFilms() = remote.getFilms()
        .map { filmList -> filmList.map { film -> film.copy(liked = local.checkLiked(film.id)) } }
        .onSuccess { local.saveFilms(it) }
        .orElse { local.getFilms() }

    suspend fun getLikedFilms() = local.getLikedFilms()

    suspend fun getFilmDetailed(id: String) = remote.getFilmDetailed(id)
        .onSuccess { local.saveFilm(it) }
        .map { it.copy(liked = local.checkLiked(id)) }
        .orElse { local.getFilmDetailed(id) }

    suspend fun saveFilm(film: Film) = local.saveFilm(film)

    suspend fun changeLikeState(id: String, newState: Boolean) = local.changeLikeState(id, newState)
}