package com.iagomichel.wefittest.home.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.iagomichel.wefittest.common.domain.entity.MovieListModel
import com.iagomichel.wefittest.common.domain.entity.MovieModel
import com.iagomichel.wefittest.home.R

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = hiltViewModel(),
    onAddProductCart: (movie: MovieModel) -> Unit
) {
    val listMovies by viewModel.movies
    val isLoading by viewModel.loading

    LaunchedEffect(Unit) {
        viewModel.fetchMovies()
    }

    HomeScreenComposable(
        modifier = modifier,
        listMovies = listMovies,
        onClickAddProducts = { movie ->
            viewModel.addCount(movie.id)
            onAddProductCart(movie)
        },
        onClickReloadHome = {
            viewModel.fetchMovies()
        },
        isLoading = isLoading,
    )
}

@Composable
private fun HomeScreenComposable(
    listMovies: MovieListModel?,
    onClickAddProducts: (movie: MovieModel) -> Unit = {},
    onClickReloadHome: () -> Unit = {},
    isLoading: Boolean,
    modifier: Modifier = Modifier
) {
    if (listMovies?.products.isNullOrEmpty() && isLoading.not()) {
        return EmptyHomeScreen(onClickReloadHome = onClickReloadHome)
    }

    if (isLoading) {
        return Box(
            contentAlignment = Alignment.TopCenter,
            modifier = Modifier
                .fillMaxSize()
                .background(colorResource(R.color.color_home_background))
        ) {
            CircularProgressIndicator(
                modifier = Modifier
                    .padding(top = 24.dp),
                color = Color.White,
                strokeWidth = 2.dp
            )
        }
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(colorResource(R.color.color_home_background))
            .padding(horizontal = 24.dp)
    ) {
        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = stringResource(R.string.more_sold),
            style = TextStyle(
                fontSize = 20.sp,
                color = Color.White
            )
        )

        Text(
            text = stringResource(R.string.big_success),
            style = TextStyle(
                fontSize = 12.sp,
                color = Color.White
            )
        )

        Spacer(modifier = Modifier.height(24.dp))

        listMovies?.products?.let { listProducts ->
            ListMovies(listProducts, onClickAddProducts)
        }
    }
}


@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    HomeScreenComposable(
        listMovies = MovieListModel(
            products = listOf(
                MovieModel(
                    id = 0,
                    title = "Titans",
                    price = 10.0,
                    image = "",
                    count = 0
                ),
                MovieModel(
                    id = 1,
                    title = "Vingadores ||",
                    price = 55.50,
                    image = "",
                    count = 1
                )
            )
        ),
        isLoading = false
    )
}
