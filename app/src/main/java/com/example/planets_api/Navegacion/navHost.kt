package com.example.planets_api.Navegacion

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.planets_api.presentacion.Planets.listPlanet.ListScreen

import com.example.planets_api.presentacion.Planets.detailPlanets.DetailScreen
import com.example.planets_api.presentacion.Planets.detailPlanets.DetailViewModel

@Composable
fun NavGraph(
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = Screen.List
    ) {
        composable<Screen.List> {
            ListScreen(
                onPlanetClick = { id ->
                    navController.navigate(Screen.Detail(id))
                }
            )
        }

        composable<Screen.Detail> {
            val viewModel: DetailViewModel = hiltViewModel()
            val state by viewModel.state.collectAsStateWithLifecycle()

            DetailScreen(
                state = state,
                onBack = { navController.popBackStack() }
            )
        }
    }
}