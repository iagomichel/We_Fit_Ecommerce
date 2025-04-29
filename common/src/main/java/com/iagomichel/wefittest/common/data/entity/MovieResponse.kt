package com.iagomichel.wefittest.common.data.entity

import com.google.gson.annotations.SerializedName
import com.iagomichel.wefittest.common.domain.entity.MovieModel

data class MovieResponse(
    @SerializedName("id") val id: Int,
    @SerializedName("title") val name: String,
    @SerializedName("price") val price: Double,
    @SerializedName("image") val image: String
) {
    fun toModel() = MovieModel(
        id = id,
        title = name,
        price = price,
        image = image
    )
}