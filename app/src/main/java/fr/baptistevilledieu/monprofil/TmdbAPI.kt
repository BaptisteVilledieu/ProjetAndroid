package fr.baptistevilledieu.monprofil

import retrofit2.http.GET
import retrofit2.http.Query

interface TmdbAPI {
    @GET("trending/movie/week")
    suspend fun lastMovies(@Query("api_key") api_key: String): TmdbMovieResult

    @GET("search/movie")
    suspend fun searchMovies(@Query("query") search: String, @Query("api_key") api_key: String): TmdbMovieResult

    @GET("trending/tv/week")
    suspend fun lastSeries(@Query("api_key") api_key: String): TmdbSerieResult

    @GET("search/tv")
    suspend fun searchSeries(@Query("query") search: String, @Query("api_key") api_key: String): TmdbSerieResult

    @GET("trending/person/week")
    suspend fun lastActors(@Query("api_key") api_key: String): TmdbActorResult

    @GET("search/person")
    suspend fun searchActors(@Query("query") search: String, @Query("api_key") api_key: String): TmdbActorResult
}

