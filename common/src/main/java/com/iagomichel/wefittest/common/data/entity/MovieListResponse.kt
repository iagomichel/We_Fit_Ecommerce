package com.iagomichel.wefittest.common.data.entity

import com.google.gson.annotations.SerializedName
import com.iagomichel.wefittest.common.domain.entity.MovieListModel

data class MovieListResponse(
    @SerializedName("products")
    val products: List<MovieResponse>
) {
    fun toModel(): MovieListModel =
        MovieListModel(
            products = products.map {
                it.toModel()
            }
        )
}
