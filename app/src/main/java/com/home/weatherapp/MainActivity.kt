package com.home.weatherapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.home.weatherapp.presentation.map_screen.MapScreen
import com.home.weatherapp.presentation.weather_screen.WeatherScreen
import com.home.weatherapp.presentation.weather_screen.WeatherScreenViewModel
import com.home.weatherapp.ui.theme.*
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    val viewModel by viewModels<WeatherScreenViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WeatherAppTheme {
                val navController = rememberNavController()
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Scaffold(
                        bottomBar = {
                            BottomNavigationBar(
                                items = listOf(
                                    BottomMenuContent(
                                        title = "Home",
                                        route = "weatherScreen",
                                        iconId = R.drawable.ic_baseline_home_24
                                    ),
                                    BottomMenuContent(
                                        title = "Map",
                                        route = "mapScreen",
                                        iconId = R.drawable.ic_baseline_map_24
                                    )
                                ),
                                navController = navController,
                                onItemClick = {
                                    navController.navigate(it.route)
                                }
                            )
                        }
                    ) {
                        Navigation(navHostController = navController)
                    }
                }
            }
        }
    }
}

@Composable
fun Navigation(
    navHostController: NavHostController
) {
    NavHost(navController = navHostController, startDestination = "weatherScreen") {
        composable("weatherScreen") {
            WeatherScreen()
        }

        composable("mapScreen") {
            MapScreen()
        }
    }
}

@Composable
fun BottomNavigationBar(
    items: List<BottomMenuContent>,
    navController: NavController,
    modifier: Modifier = Modifier,
    activeHighlightColor: Color = ButtonBlue,
    activeTextColor: Color = Color.White,
    inactiveTextColor: Color = AquaBlue,
    onItemClick: (BottomMenuContent) -> Unit
) {
    val backStackEntry = navController.currentBackStackEntryAsState()
    BottomNavigation(
        modifier = modifier.height(75.dp),
        backgroundColor = DarkBlue,
        elevation = 5.dp
    ) {
        items.forEach { item ->
            val selected = item.route == backStackEntry.value?.destination?.route
            BottomNavigationItem(selected = selected,
                onClick = { onItemClick(item) },
                icon = {
                    Column(
                        horizontalAlignment = CenterHorizontally,
                        modifier = Modifier
                            .clip(RoundedCornerShape(10.dp))
                            .background(if (selected) activeHighlightColor else Color.Transparent)
                            .padding(8.dp)
                    ) {
                        Icon(painter = painterResource(id = item.iconId), contentDescription = null)
                        Text(
                            text = item.title,
                            color = if (selected) activeTextColor else inactiveTextColor
                        )
                    }
                }
            )
        }
    }
}