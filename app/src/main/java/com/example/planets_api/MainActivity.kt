package com.example.planets_api

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Place
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation3.runtime.rememberNavBackStack
import com.example.planets_api.Navegacion.MainNavigationDisplay
import com.example.planets_api.Navegacion.Screen
import com.example.planets_api.ui.theme.Planets_ApiTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Planets_ApiTheme {

                val backStack = rememberNavBackStack (Screen.ListPlanet)
                val items = listOf(
                    TopLevelRoute("Planetas", Screen.ListPlanet, Icons.Default.Place),
                    TopLevelRoute("Personajes", Screen.ListCharacter, Icons.Default.Face)
                )

                Scaffold(
                    bottomBar = {
                        val currentDestination = backStack.lastOrNull()

                        val isDetail = currentDestination is Screen.DetailPlanet ||
                                currentDestination is Screen.DetailCharacter

                        if (!isDetail) {
                            NavigationBar {
                                items.forEach { item ->
                                    NavigationBarItem(
                                        icon = {
                                            Icon(
                                                item.icono,
                                                contentDescription = item.nombre
                                            )
                                        },
                                        label = { Text(item.nombre) },
                                        selected = currentDestination == item.ruta,
                                        onClick = {
                                            if (currentDestination != item.ruta) {
                                                backStack.clear()
                                                backStack.add(item.ruta)
                                            }
                                        }
                                    )
                                }
                            }
                        }
                    },
                    modifier = Modifier.fillMaxSize()
                ) { innerPadding ->
                    MainNavigationDisplay(
                        backStack = backStack,
                        innerPadding = innerPadding
                    )
                }
            }
        }
    }

}

data class TopLevelRoute<T : Screen>(
    val nombre: String,
    val ruta: T,
    val icono: ImageVector
)




