package com.example.planets_api.domain.planets.model


data class Planets(
    val PlanetId: Int,
    val name: String,
    val isDestroyed: Boolean,
    val description:String,
    val image: String
)