package com.example.planets_api.planets.Navegacion

import kotlinx.serialization.Serializable

sealed class Screen {
    @Serializable
    object List : Screen()

    @kotlinx.serialization.Serializable
    data class Detail(val id: Int) : Screen()
}