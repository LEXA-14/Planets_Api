package com.example.planets_api.presentacion.Planets.listPlanet

import com.example.planets_api.domain.planets.model.Planets

data class listUiState
    (
    val isLoading: Boolean=false,
    val planets:List<Planets> = emptyList(),
    val error: String?=null,
    val filterName: String = "",
    val filterIsDestroyed: Boolean?=null,



            )