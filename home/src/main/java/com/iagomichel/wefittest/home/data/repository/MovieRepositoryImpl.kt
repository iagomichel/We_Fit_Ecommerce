package com.iagomichel.wefittest.home.data.repository

import com.iagomichel.wefittest.common.domain.entity.MovieListModel
import com.iagomichel.wefittest.home.data.remote.HomeService

import com.iagomichel.wefittest.home.domain.repository.MovieRepository
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val homeService: HomeService
) : MovieRepository {

    override suspend fun fetchMovies(): MovieListModel? {
        return homeService.fetchMovies().body()?.toModel()
    }
}
