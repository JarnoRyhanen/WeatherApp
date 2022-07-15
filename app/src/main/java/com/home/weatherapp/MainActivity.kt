package com.home.weatherapp

import android.Manifest
import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.datastore.dataStore
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.home.weatherapp.appsettings.AppSettings
import com.home.weatherapp.appsettings.AppSettingsSerializer
import com.home.weatherapp.appsettings.FirstTime
import com.home.weatherapp.presentation.map_screen.MapScreen
import com.home.weatherapp.presentation.weather_screen.WeatherScreen
import com.home.weatherapp.ui.theme.AquaBlue
import com.home.weatherapp.ui.theme.ButtonBlue
import com.home.weatherapp.ui.theme.DarkBlue
import com.home.weatherapp.ui.theme.WeatherAppTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

val Context.dataStore by dataStore("app-settings.json", AppSettingsSerializer)

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private suspend fun setIsFirstTime(firstTime: FirstTime) {
        dataStore.updateData {
            it.copy(
                isFirstTime = firstTime
            )
        }
    }

    private lateinit var activityResultLauncher: ActivityResultLauncher<Array<String>>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityResultLauncher = registerForActivityResult(
            ActivityResultContracts.RequestMultiplePermissions()
        ) {}
        activityResultLauncher.launch(
            arrayOf(
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION
            )
        )

        setContent {
            WeatherAppTheme {
                val appSettings = dataStore.data.collectAsState(initial = AppSettings()).value
                val scope = rememberCoroutineScope()

                val navController = rememberNavController()
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
                    }, content = {
                        Box(modifier = Modifier.padding(it)) {
                            Navigation(
                                navHostController = navController,
                                appSettings
                            ){
                                scope.launch {
                                    setIsFirstTime(FirstTime.FALSE)
                                }
                            }
                        }
                    }
                )
            }
        }
    }
}

private const val TAG = "MainActivity"

@Composable
fun Navigation(
    navHostController: NavHostController,
    appSettings: AppSettings,
    callBack: () -> Unit

) {
    NavHost(navController = navHostController, startDestination = "weatherScreen") {
        composable("weatherScreen") {
            WeatherScreen(
                appSettings = appSettings
            ){
                callBack()
            }
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