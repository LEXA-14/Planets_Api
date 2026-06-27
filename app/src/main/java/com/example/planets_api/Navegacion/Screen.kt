package com.example.planets_api.Navegacion

import kotlinx.serialization.Serializable

sealed class Screen {
    @Serializable
    object ListPlanet : Screen()

    @Serializable
    data class DetailPlanet(val id: Int) : Screen()


    @Serializable
    object ListCharacter : Screen()

    @Serializable
    data class DetailCharacter(val id: Int) : Screen()
}