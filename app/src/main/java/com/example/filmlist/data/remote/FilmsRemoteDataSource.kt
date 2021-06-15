package com.example.filmlist.data.remote

import com.example.filmlist.core.datasource.FilmsDataSource
import com.example.filmlist.core.domain.models.Film
import com.example.filmlist.core.domain.models.FilmDetailed
import com.example.filmlist.data.mappers.toFilm
import com.example.filmlist.data.mappers.toFilmDetailed
import com.example.filmlist.data.models.FilmDetailedRemoteResponse
import com.example.filmlist.data.models.FilmRemoteResponse
import com.github.michaelbull.result.Err
import com.github.michaelbull.result.Ok
import com.github.michaelbull.result.Result

class FilmsRemoteDataSource(/* api */) : FilmsDataSource {
    private val tag = this::class.java
    private val remoteFilms = mutableListOf<FilmRemoteResponse>()
    private val remoteFilmDetailed = mutableListOf<FilmDetailedRemoteResponse>()

    override suspend fun getFilms(): Result<List<Film>, Exception> {
        return if (remoteFilms.isNotEmpty()) {
            Ok(remoteFilms.map(FilmRemoteResponse::toFilm))
        } else {
            Err(Exception("$tag: cannot get films from api"))
        }
    }

    override suspend fun getFilmDetailed(id: String): Result<FilmDetailed, Exception> {
        val film = remoteFilmDetailed.find { it.id == id }
        return if (film != null) {
            Ok(film.toFilmDetailed())
        } else {
            Err(Exception("$tag: no film cached with id - $id"))
        }
    }

    /*
        in this app films can only be saved locally and not remotely
    */
    override suspend fun saveFilms(films: List<Film>) = Err(Exception("Stub"))
    override suspend fun saveFilm(film: FilmDetailed) = Err(Exception("Stub"))
    override suspend fun saveFilm(film: Film) = Err(Exception("Stub"))
}
