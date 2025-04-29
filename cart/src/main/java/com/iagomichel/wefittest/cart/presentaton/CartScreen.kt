package com.iagomichel.wefittest.cart.presentaton

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.iagomichel.wefittest.base.ColorBackground
import com.iagomichel.wefittest.base.routes.FINISH_ROUTE
import com.iagomichel.wefittest.base.routes.HOME_ROUTE
import com.iagomichel.wefittest.cart.R
import com.iagomichel.wefittest.common.domain.entity.MovieModel

@Composable
fun CartScreen(
    viewModel: CartViewModel = hiltViewModel(),
    navHostController: NavHostController
) {
    val list by viewModel.cartProducts
    var showEmpty by remember { mutableStateOf(true) }

    CartScreenComposable(
        list = list,
        showEmpty = showEmpty,
        onClickReloadCart = {
            navHostController.navigate(HOME_ROUTE)
        },
        onClickFinishCart = {
            navHostController.navigate(FINISH_ROUTE)
            viewModel.removeItem(it)
            showEmpty = false
        },
        onIncrease = {
            viewModel.addMoreItem(it)
        },
        onDecrease = {
            viewModel.decItem(it)
        },
        onDelete = {
            viewModel.removeItem(it)
        }
    )
}

@Composable
private fun CartScreenComposable(
    list: List<MovieModel>,
    showEmpty: Boolean,
    onClickReloadCart: () -> Unit = {},
    onClickFinishCart: (idProduct: Int) -> Unit = {} ,
    onIncrease: (idProduct: Int) -> Unit = {},
    onDecrease: (idProduct: Int) -> Unit = {},
    onDelete: (idProduct: Int) -> Unit = {}
) {
    if (list.isEmpty() && showEmpty)
        return EmptyCartScreen(
            onClickReloadCart = onClickReloadCart
        )

    Column(
        modifier = Modifier
            .background(ColorBackground)
            .fillMaxSize()
            .padding(top = 24.dp)
    ) {
        Text(
            modifier = Modifier
                .padding(start = 24.dp),
            text = stringResource(R.string.cart_title),
            style = TextStyle(
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
        )

        Spacer(modifier = Modifier.height(24.dp))

        ListProducts(
            movieCart = list,
            onClickFinishCart = onClickFinishCart,
            onIncrease = onIncrease,
            onDecrease = onDecrease,
            onDelete = onDelete
        )
    }
}

@Preview
@Composable
private fun Preview() {
    CartScreenComposable(
        list = listOf(
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
        ),
        showEmpty = false
    )


}


