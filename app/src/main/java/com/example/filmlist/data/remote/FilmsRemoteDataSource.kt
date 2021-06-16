package com.example.filmlist.data.remote

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

class FilmsRemoteDataSource(/* api */) : FilmsDataSource {
    private val tag = this::class.java
    private val remoteFilms = mutableListOf(
        FilmRemoteResponse(
            id = "123123",
            poster = "",
            name = "abc",
            releaseYear = "2021",
        ),
        FilmRemoteResponse(
            id = "345",
            poster = "",
            name = "gfd",
            releaseYear = "2222",
        ),
        FilmRemoteResponse(
            id = "651",
            poster = "",
            name = "ytw",
            releaseYear = "2021",
        ),
        FilmRemoteResponse(
            id = "9545",
            poster = "",
            name = "qerwqrq",
            releaseYear = "1111",
        ),
        FilmRemoteResponse(
            id = "651a65",
            poster = "",
            name = "ytw",
            releaseYear = "2021",
        ),
        FilmRemoteResponse(
            id = "ywerebfcw",
            poster = "",
            name = "qer5123123wqrq",
            releaseYear = "1111",
        ),
        FilmRemoteResponse(
            id = "kkkksdf",
            poster = "",
            name = "zxcvzxcv",
            releaseYear = "2021",
        ),
        FilmRemoteResponse(
            id = "zcvzxcv",
            poster = "",
            name = "zxvzxvzxcv",
            releaseYear = "1111",
        ),
    )
    private val remoteFilmDetailed = mutableListOf<FilmDetailedRemoteResponse>(
        FilmDetailedRemoteResponse(
            id = "123123",
            poster = "",
            name = "abc",
            releaseYear = "2021",
            description = "asdfasdfa dsfa sdfa sdf adsf sadf asdf asdf asd fad adsf asdf asdf ",
        ),
        FilmDetailedRemoteResponse(
            id = "345",
            poster = "",
            name = "gfd",
            releaseYear = "2222",
            description = "asdfasdfa dsfa sdfa sdf adsf sadf asdf asdf asd fad adsf asdf asdf ",
        ),
        FilmDetailedRemoteResponse(
            id = "651",
            poster = "",
            name = "ytw",
            releaseYear = "2021",
            description = "asdfasdfa dsfa sdfa sdf adsf sadf asdf asdf asd fad adsf asdf asdf ",
        ),
        FilmDetailedRemoteResponse(
            id = "9545",
            poster = "",
            name = "qerwqrq",
            releaseYear = "1111",
            description = "asdfasdfa dsfa sdfa sdf adsf sadf asdf asdf asd fad adsf asdf asdf ",
        ),
        FilmDetailedRemoteResponse(
            id = "651a65",
            poster = "",
            name = "ytw",
            releaseYear = "2021",
            description = "asdfasdfa dsfa sdfa sdf adsf sadf asdf asdf asd fad adsf asdf asdf ",
        ),
        FilmDetailedRemoteResponse(
            id = "ywerebfcw",
            poster = "",
            name = "qer5123123wqrq",
            releaseYear = "1111",
            description = "asdfasdfa dsfa sdfa sdf adsf sadf asdf asdf asd fad adsf asdf asdf ",
        ),
        FilmDetailedRemoteResponse(
            id = "kkkksdf",
            poster = "",
            name = "zxcvzxcv",
            releaseYear = "2021",
            description = "asdfasdfa dsfa sdfa sdf adsf sadf asdf asdf asd fad adsf asdf asdf ",
        ),
        FilmDetailedRemoteResponse(
            id = "zcvzxcv",
            poster = "",
            name = "zxvzxvzxcv",
            releaseYear = "1111",
            description = "asdfasdfa dsfa sdfa sdf adsf sadf asdf asdf asd fad adsf asdf asdf ",
        ),
    )

    override suspend fun getFilms(): Result<List<Film>, Exception> {
        return if (remoteFilms.isNotEmpty()) {
            Ok(remoteFilms.map(FilmRemoteResponse::toFilm))
        } else {
            Err(Exception("$tag: cannot get films from api"))
        }
    }

    override suspend fun getLikedFilms() = Err(Exception("Can't get liked films remotely"))

    override suspend fun getFilmDetailed(id: String): Result<FilmDetailed, Exception> {
        val film = remoteFilmDetailed.find { it.id == id }
        return if (film != null) {
            Ok(film.toFilmDetailed())
        } else {
            Err(Exception("$tag: no film cached with id - $id"))
        }
    }

    /*
        in this app films can only be saved locally and not remotely(may be in the future)
    */
    override suspend fun saveFilms(films: List<Film>) = Unit
    override suspend fun saveFilm(film: FilmDetailed) = Unit
    override suspend fun saveFilm(film: Film) = Unit
    override suspend fun changeLikeState(id: String, newState: Boolean) = Unit
    override suspend fun checkLiked(id: String) = false
}
