package com.iagomichel.wefittest.cart.presentaton

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.iagomichel.wefittest.common.domain.entity.MovieModel

@Composable
fun ListProducts(
    movieCart: List<MovieModel>,
    onClickFinishCart: (idProduct: Int) -> Unit = {},
    onIncrease: (idProduct: Int) -> Unit = {},
    onDecrease: (idProduct: Int) -> Unit = {},
    onDelete: (idProduct: Int) -> Unit = {}
) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        itemsIndexed(
            items = movieCart,
            key = { _, item ->
                item.id
            }
        ) { index, movie ->
            val isLastItem = index == movieCart.lastIndex

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        start = 24.dp,
                        end = 24.dp,
                        bottom = if (isLastItem)
                            24.dp
                        else 0.dp
                    ),
                shape = RoundedCornerShape(4.dp)
            ) {
                ProductItem(
                    movieCart = movie,
                    onClickFinishCart = onClickFinishCart,
                    onIncrease = onIncrease,
                    onDecrease = onDecrease,
                    onDelete = onDelete
                )
            }
        }
    }
}

@Preview
@Composable
private fun Preview() {
    MaterialTheme {
        ListProducts(
            movieCart = listOf(
                MovieModel(
                id = 1,
                title = "Hulk",
                price = 21.5,
                image = "",
                count = 3
                ),
                MovieModel(
                    id = 2,
                    title = "Vingadores",
                    price = 2.2,
                    image = "",
                    count = 2
                ),
            )
        )
    }
}
