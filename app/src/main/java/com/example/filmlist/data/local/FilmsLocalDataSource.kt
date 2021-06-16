package com.example.filmlist.data.local

import com.example.filmlist.core.datasource.FilmsDataSource
import com.example.filmlist.core.domain.models.Film
import com.example.filmlist.core.domain.models.FilmDetailed
import com.example.filmlist.data.models.FilmLocal
import com.example.filmlist.data.models.toFilm
import com.example.filmlist.data.models.toFilmDetailed
import com.example.filmlist.data.models.toFilmLocal
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

    override suspend fun getLikedFilms(): Result<List<Film>, Exception> {
        val films = cachedFilms.filter { it.liked }
        return if (films.isNotEmpty()) {
            Ok(films.map(FilmLocal::toFilm))
        } else {
            Err(Exception("$tag: no film cached"))
        }
    }

    override suspend fun getFilmDetailed(id: Int): Result<FilmDetailed, Exception> {
        val film = cachedFilms.find { it.id == id }
        return if (film != null) {
            Ok(film.toFilmDetailed())
        } else {
            Err(Exception("$tag: no film cached with id - $id"))
        }
    }

    override suspend fun saveFilms(films: List<Film>){
        // in room it will be "onConflictStrategy.Replace"
        // and bug with duplicated files will disappear
        cachedFilms.addAll(films.map(Film::toFilmLocal))
    }

    override suspend fun saveFilm(film: FilmDetailed) {
        // in room it will be "onConflictStrategy.Replace"
        cachedFilms.add(film.toFilmLocal())
    }

    override suspend fun saveFilm(film: Film) {
        // in room it will be "onConflictStrategy.Replace"
        cachedFilms.add(film.toFilmLocal())
    }

    override suspend fun changeLikeState(id: Int, newState: Boolean) {
        cachedFilms.find { it.id == id }?.apply { liked = newState }
    }

    override suspend fun checkLiked(id: Int): Boolean {
        return id in cachedFilms.filter { it.liked }.map { it.id }
    }
}
