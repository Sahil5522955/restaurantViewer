package com.example.restaurant_reviewer

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.restaurant_reviewer.ui.navigation.NavigationItem
import com.example.restaurant_reviewer.ui.navigation.Screen
import com.example.restaurant_reviewer.ui.screen.ProfileViewModel
import com.example.restaurant_reviewer.ui.screen.detail.DetailScreen
import com.example.restaurant_reviewer.ui.screen.favorite.FavoriteScreen
import com.example.restaurant_reviewer.ui.screen.home.HomeScreen
import com.example.restaurant_reviewer.ui.theme.RestaurantAppTheme

@Composable
fun RestaurantApp(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    navigateToHome: () -> Unit
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    Scaffold(
        bottomBar = {
            if (currentRoute != Screen.DetailRestaurant.route) {
                BottomBar(navController)
            }
        },
        modifier = modifier
    ) { innerPadding ->

        NavHost(
            navController = navController,
            startDestination = Screen.Home.route,
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            composable(Screen.Home.route) {
                HomeScreen(
                    navigateToDetail = { id ->
                        navController.navigate(Screen.DetailRestaurant.createRoute(id))
                    },
                    modifier = Modifier.background(color = MaterialTheme.colors.background)
                )
            }

            composable(Screen.Favorite.route) {
                FavoriteScreen(
                    navigateToDetail = { id ->
                        navController.navigate(Screen.DetailRestaurant.createRoute(id))
                    },
                    modifier = Modifier.background(color = MaterialTheme.colors.background)
                )
            }

            composable(Screen.Profile.route) {
                ProfileScreen(navigateToHome = navigateToHome)
            }

            composable(
                route = Screen.DetailRestaurant.route,
                arguments = listOf(navArgument("id") { type = NavType.StringType }),
            ) {
                val id = it.arguments?.getString("id") ?: ""
                DetailScreen(
                    id = id,
                    navigateBack = { navController.navigateUp() },
                )
            }
        }
    }
}

@Composable
fun ProfileScreen(modifier: Modifier = Modifier, navigateToHome: () -> Unit, profileViewModel: ProfileViewModel = hiltViewModel()) {
    val context = LocalContext.current
    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Button(onClick = {
            profileViewModel.deleteAllFavs()
            navigateToHome()
        }) {
            Text(text = "Logout")
        }
    }
}

@Composable
fun BottomBar(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    BottomNavigation(
        modifier = modifier
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        val navigationItem = listOf(
            NavigationItem(
                title = stringResource(R.string.menu_home),
                icon = Icons.Default.Home,
                screen = Screen.Home
            ),
            NavigationItem(
                title = stringResource(R.string.menu_favorite),
                icon = Icons.Default.Favorite,
                screen = Screen.Favorite
            ),
            NavigationItem(
                title = stringResource(R.string.profile_txt),
                icon = Icons.Default.AccountCircle,
                screen = Screen.Profile
            )
        )

        BottomNavigation {
            navigationItem.map { item ->
                BottomNavigationItem(
                    icon = {
                        Icon(
                            imageVector = item.icon,
                            contentDescription = if (item.title == "Profile") "about_page" else item.title
                        )
                    },
                    label = { Text(item.title) },
                    selected = currentRoute == item.screen.route,
                    onClick = {
                        navController.navigate(item.screen.route) {
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            restoreState = true
                            launchSingleTop = true
                        }
                    }
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RestaurantAppPreview() {
    RestaurantAppTheme {
        RestaurantApp(navigateToHome = {
        })
    }
}