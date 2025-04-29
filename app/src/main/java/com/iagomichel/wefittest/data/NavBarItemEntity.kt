package com.iagomichel.wefittest.data

import com.iagomichel.wefittest.R
import com.iagomichel.wefittest.base.routes.PROFILE_ROUTE
import com.iagomichel.wefittest.base.routes.HOME_ROUTE
import com.iagomichel.wefittest.base.routes.CART_ROUTE

private const val CART = "Carrinho"
private const val HOME = "Home"
private const val PROFILE = "Perfil"

sealed class NavBarItemEntity(
    val route: String,
    val icon: Int,
    val title: String
) {
    data object Cart: NavBarItemEntity(
        route = CART_ROUTE,
        icon = R.drawable.ic_cart,
        title = CART
    )

    data object Home: NavBarItemEntity(
        route = HOME_ROUTE,
        icon = R.drawable.ic_home,
        title = HOME
    )

    data object Profile: NavBarItemEntity(
        route = PROFILE_ROUTE,
        icon = R.drawable.ic_profile,
        title = PROFILE
    )
}
