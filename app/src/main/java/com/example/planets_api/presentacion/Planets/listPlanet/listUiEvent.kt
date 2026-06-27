package com.example.planets_api.presentacion.Planets.listPlanet


sealed interface listEvent {

    data class UpdateFilters(
        val name: String,
        val isDestroyed: Boolean?,
    ) : listEvent

    data object Search : listEvent
}
