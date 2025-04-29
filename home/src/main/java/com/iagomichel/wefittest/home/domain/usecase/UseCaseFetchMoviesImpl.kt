package com.iagomichel.wefittest.home.domain.usecase

import com.iagomichel.wefittest.common.domain.entity.MovieListModel
import com.iagomichel.wefittest.home.domain.repository.MovieRepository
import javax.inject.Inject

class UseCaseFetchMoviesImpl @Inject constructor(
    private val movieRepository: MovieRepository
) : UseCaseFetchMovies {

    override suspend fun invoke(): MovieListModel? =
        movieRepository.fetchMovies()
}
