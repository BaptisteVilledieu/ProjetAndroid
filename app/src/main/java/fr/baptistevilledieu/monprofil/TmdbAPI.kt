package fr.baptistevilledieu.monprofil

import retrofit2.http.GET
import retrofit2.http.Query

interface TmdbAPI {
    @GET("trending/movie/week")
    suspend fun lastMovies(@Query("api_key") api_key: String): TmdbMovieResult

    @GET("search/movie")
    suspend fun searchMovies(@Query("query") search: String, @Query("api_key") api_key: String): TmdbMovieResult
}

