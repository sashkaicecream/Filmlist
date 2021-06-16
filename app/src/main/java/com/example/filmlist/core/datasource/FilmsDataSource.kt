package com.example.filmlist.core.datasource

import com.example.filmlist.core.domain.models.Film
import com.example.filmlist.core.domain.models.FilmDetailed
import com.github.michaelbull.result.Result

interface FilmsDataSource {
    suspend fun getFilms(): Result<List<Film>, Exception>
    suspend fun getLikedFilms(): Result<List<Film>, Exception>
    suspend fun getFilmDetailed(id: String): Result<FilmDetailed, Exception>
    suspend fun saveFilms(films: List<Film>)
    suspend fun saveFilm(film: FilmDetailed)
    suspend fun saveFilm(film: Film)
    suspend fun changeLikeState(id: String, newState: Boolean)
    suspend fun checkLiked(id: String): Boolean
}
