package com.iagomichel.wefittest.home.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.iagomichel.wefittest.common.domain.entity.MovieModel
import com.iagomichel.wefittest.home.R

@Composable
fun ListMovies(
    listProducts: List<MovieModel>,
    onClickAddProducts: (MovieModel) -> Unit
) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        itemsIndexed(
            items = listProducts,
            key = { _, item -> item.id }
        ) { index, movie ->
            val isLastItem = index == listProducts.lastIndex

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        bottom = if (isLastItem)
                            24.dp
                        else 0.dp
                    ),
                shape = RoundedCornerShape(4.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(180.dp)
                            .background(
                                Color.White,
                                shape = RoundedCornerShape(8.dp)
                            )
                    ) {
                        AsyncImage(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(180.dp),
                            model = movie.image,
                            contentDescription = null
                        )
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    Box(modifier = Modifier.fillMaxWidth()) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text(
                                text = movie.title,
                                style = TextStyle(
                                    fontSize = 12.sp
                                ),
                                modifier = Modifier.padding(bottom = 4.dp)
                            )

                            Text(
                                text = stringResource(R.string.dollar_sign) + movie.price.toString(),
                                style = TextStyle(
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.Bold
                                )
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    ButtonAddCart(onClickAddProducts, movie)
                }
            }
        }
    }
}

@Composable
private fun ButtonAddCart(
    onClickAddProducts: (MovieModel) -> Unit,
    movie: MovieModel
) {
    Button(
        onClick = {
            onClickAddProducts(movie)
        },
        modifier = Modifier.fillMaxWidth(),
        colors =
            if (movie.count == 0) ButtonDefaults.buttonColors(
                containerColor = colorResource(
                    id = R.color.color_home_button_unselected
                )
            ) else ButtonDefaults.buttonColors(
                containerColor = colorResource(
                    id = R.color.color_home_button_selected
                )
            ),
        shape = RoundedCornerShape(4.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(id = R.drawable.add_cart),
                contentDescription = "",
                tint = Color.White
            )

            Spacer(modifier = Modifier.width(3.4.dp))

            Text(
                text = movie.count.toString(),
                color = Color.White
            )

            Spacer(modifier = Modifier.width(12.dp))

            Text(
                text = stringResource(R.string.add_to_cart),
                color = Color.White
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewListMovies() {
    ListMovies(
        listProducts = listOf(
            MovieModel(
                id = 1,
                title = "O Senhor dos Anéis",
                price = 29.99,
                image = "",
                count = 0
            ),
            MovieModel(
                id = 2,
                title = "Interestelar",
                price = 19.99,
                image = "",
                count = 1
            )
        ),
        onClickAddProducts = {}
    )
}
