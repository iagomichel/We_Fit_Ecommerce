package com.iagomichel.wefittest.home.data.remote

import com.iagomichel.wefittest.common.data.entity.MovieListResponse
import retrofit2.Response
import retrofit2.http.GET

interface HomeService {
    @GET("https://wefit-movies.vercel.app/api/movies")
    suspend fun fetchMovies(): Response<MovieListResponse>
}
