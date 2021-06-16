package com.example.filmlist.data.remote

import com.example.filmlist.data.models.FilmDetailedRemoteResponse
import com.example.filmlist.data.models.FilmsResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface FilmsApi {
    @GET("trending/movies/week")
    suspend fun getFilms(
        @Query("api_key") key: String,
        @Query("page") page: Int = 1
    ): FilmsResponse

    @GET("movie/{movie_id}")
    suspend fun getFilmDetailed(
        @Path("movie_id") id: Int,
        @Query("api_key") key: String,
    ): FilmDetailedRemoteResponse
}
