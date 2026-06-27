package com.example.planets_api.Navegacion

import kotlinx.serialization.Serializable

sealed class Screen {
    @Serializable
    object List : Screen()

    @Serializable
    data class Detail(val id: Int) : Screen()
}