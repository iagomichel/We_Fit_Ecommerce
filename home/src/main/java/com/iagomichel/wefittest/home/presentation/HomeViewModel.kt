package com.iagomichel.wefittest.home.presentation

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import com.iagomichel.wefittest.base.WeFitViewModel
import com.iagomichel.wefittest.common.domain.entity.MovieListModel
import com.iagomichel.wefittest.common.domain.entity.MovieModel
import com.iagomichel.wefittest.home.domain.usecase.UseCaseFetchMovies
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val useCaseFetchMovies: UseCaseFetchMovies
): WeFitViewModel() {

    private val _movies = mutableStateOf<MovieListModel?>(null)
    val movies: State<MovieListModel?> = _movies

    private val _showNavBar = mutableStateOf(false)
    val showNavBar: State<Boolean> = _showNavBar

    fun fetchMovies() {
        launchWeFitCoroutine {
            val response = useCaseFetchMovies.invoke()
            _movies.value = response

            showNavBar()
        }
    }

    fun addCount(idProduct: Int) {
        launchWeFitCoroutine {
            val list = _movies.value

            val newList =
                list?.copy(
                    products = list.products.map {
                        MovieModel(
                            id = it.id,
                            title = it.title,
                            price = it.price,
                            image = it.image,
                            count = if (it.id == idProduct) it.count + 1 else it.count
                        )
                    }
                )
            _movies.value = newList
        }
    }

    private fun showNavBar() {
        launchWeFitCoroutine {
            _showNavBar.value = true
        }
    }
}
