package com.example.filmlist.core.domain.repository

import com.example.filmlist.core.datasource.FilmsDataSource
import com.example.filmlist.core.domain.models.Film
import com.github.michaelbull.result.onSuccess
import com.github.michaelbull.result.orElse

class FilmsRepository(
    private val local: FilmsDataSource,
    private val remote: FilmsDataSource,
) {
    suspend fun getFilms() = remote.getFilms()
        .onSuccess { local.saveFilms(it) }
        .orElse { local.getFilms() }

    suspend fun getFilmDetailed(id: String) = remote.getFilmDetailed(id)
        .onSuccess { local.saveFilm(it) }
        .orElse { local.getFilmDetailed(id) }

    suspend fun saveFilm(film: Film) = local.saveFilm(film)
}