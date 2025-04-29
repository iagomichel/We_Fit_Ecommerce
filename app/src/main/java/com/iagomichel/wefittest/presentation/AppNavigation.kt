package com.iagomichel.wefittest.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.iagomichel.wefittest.data.NavBarItemEntity
import com.iagomichel.wefittest.R
import com.iagomichel.wefittest.cart.presentaton.CartScreen
import com.iagomichel.wefittest.cart.presentaton.CartViewModel
import com.iagomichel.wefittest.home.presentation.HomeScreen
import com.iagomichel.wefittest.home.presentation.HomeViewModel
import com.iagomichel.wefittest.presentation.navbar.NavBar
import com.iagomichel.wefittest.base.routes.CART_ROUTE
import com.iagomichel.wefittest.base.routes.FINISH_ROUTE
import com.iagomichel.wefittest.base.routes.HOME_ROUTE
import com.iagomichel.wefittest.base.routes.PROFILE_ROUTE
import com.iagomichel.wefittest.cart.presentaton.PurchaseMadeScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    val itemsNavBar = listOf(
        NavBarItemEntity.Cart,
        NavBarItemEntity.Home,
        NavBarItemEntity.Profile
    )

    val homeViewModel: HomeViewModel = hiltViewModel()
    val cartViewModel: CartViewModel = hiltViewModel()

    val showNavBar by homeViewModel.showNavBar

    Scaffold(
        topBar = {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .background(colorResource(R.color.black_top_bar))
                    .fillMaxWidth()
                    .padding(vertical = 24.dp)
            ) {
                Text(
                    text ="WeMovies",
                    style = MaterialTheme.typography.titleLarge.copy(color = Color.White)
                )
            }
        },
        bottomBar = {
            if (showNavBar) {
                NavBar(
                    navController = navController,
                    items = itemsNavBar
                )
            }
        }
    ) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {
            NavHost(
                navController = navController,
                startDestination = HOME_ROUTE
            ) {
                composable(HOME_ROUTE) {
                    HomeScreen(
                        viewModel = homeViewModel,
                        onAddProductCart = { movie ->
                            cartViewModel.addMovieCart(movie)
                        }
                    )
                }
                composable(CART_ROUTE) {
                    CartScreen(
                        viewModel = cartViewModel,
                        navHostController = navController
                    )
                }
                composable(PROFILE_ROUTE) {

                }
                composable(FINISH_ROUTE) {
                    PurchaseMadeScreen(
                        onClickReloadHome = {
                            navController.navigate(HOME_ROUTE)
                        }
                    )
                }
            }
        }
    }
}
