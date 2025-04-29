package com.iagomichel.wefittest.common.domain.entity

data class MovieModel(
    val id: Int,
    val title: String,
    val price: Double,
    val image: String,
    val count: Int = 0,
    val date: String = ""
)
