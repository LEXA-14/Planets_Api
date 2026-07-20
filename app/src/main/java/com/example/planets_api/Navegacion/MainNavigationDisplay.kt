package com.example.planets_api.Navegacion

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import com.example.planets_api.presentacion.Character.Detail.CharacterDetailScreen
import com.example.planets_api.presentacion.Character.List.ListScreenCharacter
import com.example.planets_api.presentacion.Planets.detailPlanets.PlanetDetailScreen
import com.example.planets_api.presentacion.Planets.listPlanet.ListScreen
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import kotlinx.datetime.format.Padding

@Composable
fun MainNavigationDisplay(
    backStack: NavBackStack<NavKey>,
    innerPadding: PaddingValues
){
    NavDisplay(
        backStack=backStack,
        modifier = Modifier.fillMaxSize(),
        entryProvider = entryProvider {

            entry<Screen.ListPlanet>{
                ListScreen(
                    onPlanetClick = { id ->
                        backStack.add(Screen.DetailPlanet(id))
                    },
                    onNavigateToCharacters = { backStack.add(Screen.ListCharacter) },
                )
            }

            entry<Screen.DetailPlanet> {key->
                PlanetDetailScreen(
                    planetId = key.id,
                    onBack = {
                        if (backStack.isNotEmpty()) {
                            backStack.removeAt(backStack.size - 1)
                        }
                    },

                )
            }
            entry<Screen.ListCharacter>{
                ListScreenCharacter(
                    onCharacterClick = {id->
                        backStack.add(Screen.DetailCharacter(id))
                    }

                )
            }
            entry<Screen.DetailCharacter> {
                key->
                CharacterDetailScreen(
                    characterId=key.id,
                    onBack={
                        if(backStack.isNotEmpty()) backStack.removeAt(backStack.size-1)
                    }
                )
            }
        }
    )


}