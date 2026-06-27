package com.example.planets_api.Navegacion

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.planets_api.presentacion.Character.List.ListScreenCharacter
import com.example.planets_api.presentacion.Planets.listPlanet.ListScreen

import com.example.planets_api.presentacion.Planets.detailPlanets.DetailScreen
import com.example.planets_api.presentacion.Planets.detailPlanets.DetailViewModel

@Composable
fun NavGraph(
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = Screen.ListPlanet
    ) {
        composable<Screen.ListPlanet> {
            ListScreen(
                onPlanetClick = { id ->
                    navController.navigate(Screen.DetailPlanet(id))
                },
                onNavigateToCharacters = {
                    navController.navigate(Screen.ListCharacter)
                }
            )
        }

        composable<Screen.DetailPlanet> {
            val viewModel: DetailViewModel = hiltViewModel()
            val state by viewModel.state.collectAsStateWithLifecycle()

            DetailScreen(
                state = state,
                onBack = { navController.popBackStack() }
            )
        }
        composable<Screen.ListCharacter> {
            ListScreenCharacter(
             onCharacterClick = {id->
                 navController.navigate(Screen.DetailCharacter(id))
             }
            )
        }

        composable<Screen.DetailCharacter> {
            val viewModel: DetailViewModel = hiltViewModel()
            val state by viewModel.state.collectAsStateWithLifecycle()

            DetailScreen(
                state = state,
                onBack = { navController.popBackStack() }
            )
        }
    }
}