package fr.baptistevilledieu.monprofil

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material3.windowsizeclass.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.BlendMode.Companion.Screen
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import fr.baptistevilledieu.monprofil.ui.theme.MonProfilTheme
import kotlin.math.log
import androidx.lifecycle.viewmodel.compose.viewModel as viewModel

class MainActivity : ComponentActivity() {
    @SuppressLint("SuspiciousIndentation")
    @OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            val model:MainViewModel = viewModel()
            val windowSizeClass = calculateWindowSizeClass(this)
            val navBackStackEntry by navController.currentBackStackEntryAsState()
            val currentDestination = navBackStackEntry?.destination
            Scaffold(
                bottomBar = {
                    if (currentDestination != null && currentDestination?.hierarchy?.any { it.route == "profil" } == false) {
                        BottomNavigation (backgroundColor = Color.Blue, contentColor = Color.White) {
                            BottomNavigationItem(
                                icon = { Icon(painter = painterResource(id = R.drawable.iconfilms), contentDescription ="icon des films", modifier = Modifier.size(27.dp) ) },
                                label = { Text(text = "Films") },
                                selected = currentDestination?.route == "films" == true,
                                onClick = { navController.navigate("films") }
                            )
                            BottomNavigationItem(
                                icon = { Icon(painter = painterResource(id = R.drawable.iconseries), contentDescription ="icon des séries", modifier = Modifier.size(27.dp) )
                                },
                                label = { Text(text = "Séries") },
                                selected = currentDestination?.route == "series" == true,
                                onClick = { navController.navigate("series")}
                            )
                            BottomNavigationItem(
                                icon = { Icon(painter = painterResource(id = R.drawable.iconacteurs), contentDescription ="icon des acteurs", modifier = Modifier.size(27.dp) )},
                                label = { Text(text = "Acteurs") },
                                selected = currentDestination?.route == "acteurs" == true,
                                onClick = { navController.navigate("acteurs") }
                            )
                        }
                    }
                }
                        ){ innerPadding ->
                        NavHost(
                            navController = navController,
                            startDestination = "profil", modifier = Modifier.padding(innerPadding)
                        ) {
                            composable("profil") {
                                Profil(windowSizeClass, navController)
                            }
                            composable("films") {
                                Films(model)
                            }
                        }
                    }
                }
        }
        }


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MonProfilTheme {
        val navController = rememberNavController()
        Home(navController)
    }
}





