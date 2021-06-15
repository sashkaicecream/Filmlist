package com.example.filmlist.core.datasource

import com.example.filmlist.core.domain.models.Film
import com.example.filmlist.core.domain.models.FilmDetailed
import com.github.michaelbull.result.Result

interface FilmsDataSource {
    suspend fun getFilms(): Result<List<Film>, Exception>
    suspend fun getFilmDetailed(id: String): Result<FilmDetailed, Exception>
    suspend fun saveFilms(films: List<Film>): Result<Unit, Exception>
    suspend fun saveFilm(film: FilmDetailed): Result<Unit, Exception>
    suspend fun saveFilm(film: Film): Result<Unit, Exception>
}
