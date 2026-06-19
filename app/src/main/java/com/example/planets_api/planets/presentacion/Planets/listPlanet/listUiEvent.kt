package com.example.planets_api.planets.presentacion.Planets.listPlanet


sealed interface listEvent {

    data class UpdateFilters(
        val name: String,
        val isDestroyed: String,
    ) : listEvent

    data object Search : listEvent
}
