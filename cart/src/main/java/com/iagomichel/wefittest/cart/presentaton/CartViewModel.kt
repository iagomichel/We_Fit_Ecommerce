package com.iagomichel.wefittest.cart.presentaton

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import com.iagomichel.wefittest.base.WeFitViewModel
import com.iagomichel.wefittest.base.util.getDateNowYearMonthDay
import com.iagomichel.wefittest.common.domain.entity.MovieModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor() : WeFitViewModel() {

    private val _cartProducts = mutableStateOf<List<MovieModel>>(listOf())
    val cartProducts: State<List<MovieModel>> = _cartProducts

    fun addMovieCart(movie: MovieModel) {
        launchWeFitCoroutine {
            val listCart: MutableList<MovieModel> = _cartProducts.value.map {
                MovieModel(
                    id = it.id,
                    title = it.title,
                    price = it.price,
                    image = it.image,
                    count = it.count,
                    date = it.date
                )
            }.toMutableList()

            val currentIndex = listCart.indexOfFirst {
                it.id == movie.id
            }

            if (currentIndex > -1) {
                val currentCount = listCart[currentIndex].count

                val updateMovie = movie.copy(
                    count = currentCount + 1,
                    date = getDateNowYearMonthDay()
                )

                listCart[currentIndex] = updateMovie
            } else {
                val newMovie = movie.copy(
                    count = movie.count + 1,
                    date = getDateNowYearMonthDay()
                )

                listCart.add(newMovie)
            }

            _cartProducts.value = listCart
        }
    }

    fun addMoreItem(idProduct: Int) {
        launchWeFitCoroutine {
            val list = _cartProducts.value.toMutableList()

            val newList =
                list.map {
                    MovieModel(
                        id = it.id,
                        title = it.title,
                        price = it.price,
                        image = it.image,
                        count = if (it.id == idProduct) it.count + 1 else it.count,
                        date = getDateNowYearMonthDay()
                    )
                }
            _cartProducts.value = newList
        }
    }

    fun decItem(idProduct: Int) {
        launchWeFitCoroutine {
            val list = _cartProducts.value.toMutableList()

            val newList =
                list.map {
                    MovieModel(
                        id = it.id,
                        title = it.title,
                        price = it.price,
                        image = it.image,
                        count = if (it.id == idProduct) it.count - 1 else it.count,
                        date = it.date
                    )
                }
            _cartProducts.value = newList
        }
    }

    fun removeItem(idProduct: Int) {
        launchWeFitCoroutine {
            val list = _cartProducts.value.toMutableList()

            val listRemoved = list.filterNot {
                it.id == idProduct
            }

            _cartProducts.value = listRemoved
        }
    }
}