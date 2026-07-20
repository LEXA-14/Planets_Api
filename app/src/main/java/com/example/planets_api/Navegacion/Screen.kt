package com.example.planets_api.Navegacion

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

@Serializable
sealed class Screen : NavKey{
    @Serializable
    object ListPlanet : Screen()

    @Serializable
    data class DetailPlanet(val id: Int) : Screen()


    @Serializable
    object ListCharacter : Screen()

    @Serializable
    data class DetailCharacter(val id: Int) : Screen()
}