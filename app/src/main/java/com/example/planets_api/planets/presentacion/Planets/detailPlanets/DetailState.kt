package com.example.planets_api.planets.presentacion.Planets.detailPlanets

import com.example.planets_api.planets.domain.planets.model.Planets

data class DetailState (
    val isLoading: Boolean = false,
    val planet: Planets? = null,
    val error: String? = null
)