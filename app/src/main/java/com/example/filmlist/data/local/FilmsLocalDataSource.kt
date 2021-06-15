package com.example.filmlist.data.local

import com.example.filmlist.core.datasource.FilmsDataSource
import com.example.filmlist.core.domain.models.Film
import com.example.filmlist.core.domain.models.FilmDetailed
import com.example.filmlist.data.mappers.toFilm
import com.example.filmlist.data.mappers.toFilmDetailed
import com.example.filmlist.data.mappers.toFilmLocal
import com.example.filmlist.data.models.FilmLocal
import com.github.michaelbull.result.Err
import com.github.michaelbull.result.Ok
import com.github.michaelbull.result.Result

class FilmsLocalDataSource(/* dao */) : FilmsDataSource {
    private val tag = this::class.java
    private val cachedFilms = mutableListOf<FilmLocal>()

    override suspend fun getFilms(): Result<List<Film>, Exception> {
        return if (cachedFilms.isNotEmpty()) {
            Ok(cachedFilms.map(FilmLocal::toFilm))
        } else {
            Err(Exception("$tag: no film cached"))
        }
    }

    override suspend fun getFilmDetailed(id: String): Result<FilmDetailed, Exception> {
        val film = cachedFilms.find { it.id == id }
        return if (film != null) {
            Ok(film.toFilmDetailed())
        } else {
            Err(Exception("$tag: no film cached with id - $id"))
        }
    }

    override suspend fun saveFilms(films: List<Film>): Result<Unit, Exception> {
        cachedFilms.addAll(films.map(Film::toFilmLocal))
        return Ok(Unit)
    }

    override suspend fun saveFilm(film: FilmDetailed): Result<Unit, Exception> {
        cachedFilms.add(film.toFilmLocal())
        return Ok(Unit)
    }

    override suspend fun saveFilm(film: Film): Result<Unit, Exception> {
        cachedFilms.add(film.toFilmLocal())
        return Ok(Unit)
    }
}
