package com.example.filmlist.data.remote

import com.example.filmlist.BuildConfig
import com.example.filmlist.core.datasource.FilmsDataSource
import com.example.filmlist.core.domain.models.Film
import com.example.filmlist.core.domain.models.FilmDetailed
import com.example.filmlist.data.models.FilmDetailedRemoteResponse
import com.example.filmlist.data.models.FilmRemoteResponse
import com.example.filmlist.data.models.toFilm
import com.example.filmlist.data.models.toFilmDetailed
import com.github.michaelbull.result.Err
import com.github.michaelbull.result.Ok
import com.github.michaelbull.result.Result

class FilmsRemoteDataSource(private val filmsApi: FilmsApi) : FilmsDataSource {
    private val tag = this::class.java
    private val apiKey = BuildConfig.API_KEY

    override suspend fun getFilms() = try {
        val films = filmsApi.getFilms(apiKey)
        Ok(films.results.map(FilmRemoteResponse::toFilm))
    } catch (e: Exception) {
        Err(Exception("$tag: cannot get films from api - err: $e"))
    }


    override suspend fun getFilmDetailed(id: Int) = try {
        val film = filmsApi.getFilmDetailed(id, apiKey)
        Ok(film.toFilmDetailed())
    } catch (e: Exception) {
        val a = Err(Exception("$tag: no film cached with id - $id, err - $e"))
        a
    }

    /*
        in this app films can only be saved locally and not remotely(may be in the future)
    */
    override suspend fun getLikedFilms() = Err(Exception("Can't get liked films remotely"))
    override suspend fun saveFilms(films: List<Film>) = Unit
    override suspend fun saveFilm(film: FilmDetailed) = Unit
    override suspend fun saveFilm(film: Film) = Unit
    override suspend fun changeLikeState(id: Int, newState: Boolean) = Unit
    override suspend fun checkLiked(id: Int) = false
}
