package com.iagomichel.wefittest.home.domain.repository

import com.iagomichel.wefittest.common.domain.entity.MovieListModel

interface MovieRepository {
    suspend fun fetchMovies(): MovieListModel?
}
