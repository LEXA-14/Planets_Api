package com.example.planets_api.presentacion.Planets.detailPlanets

import com.example.planets_api.domain.planets.model.Planets

data class DetailState (
    val isLoading: Boolean = false,
    val planet: Planets? = null,
    val error: String? = null
)