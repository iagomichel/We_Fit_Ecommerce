package com.iagomichel.wefittest.home.domain.usecase

import com.iagomichel.wefittest.common.domain.entity.MovieListModel

interface UseCaseFetchMovies {
    suspend operator fun invoke(): MovieListModel?
}