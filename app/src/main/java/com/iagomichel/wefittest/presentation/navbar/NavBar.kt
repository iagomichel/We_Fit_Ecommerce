package com.iagomichel.wefittest.presentation.navbar

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.iagomichel.wefittest.base.routes.PROFILE_ROUTE
import com.iagomichel.wefittest.data.NavBarItemEntity

@Composable
fun NavBar(
    navController: NavController,
    items: List<NavBarItemEntity>
) {
    NavigationBar(
        containerColor = Color.Black
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        var isSelected: Boolean

        items.forEach { item ->
            isSelected = currentRoute == item.route

            NavigationBarItem(
                colors = NavigationBarItemDefaults.colors(
                    indicatorColor = Color.Transparent,
                    disabledIconColor = Color.Gray,
                    disabledTextColor = Color.Gray),
                icon = {
                    Icon(
                        painterResource(id = item.icon),
                        contentDescription = item.title,
                        modifier = Modifier.size(20.dp),
                        tint =  if (item.route == PROFILE_ROUTE) Color.Gray else Color.White
                    )
                },
                label = {
                    Text(
                        text = item.title,
                        fontSize = 12.sp,
                        color = if (item.route == PROFILE_ROUTE) Color.Gray else Color.White,
                        modifier = Modifier.padding(top = 2.dp)
                    )
                },
                selected = isSelected,
                onClick = {
                    navController.navigate(item.route) {
                        popUpTo(navController.graph.startDestinationId) { saveState = true }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                enabled = item.route != PROFILE_ROUTE
            )
        }
    }
}