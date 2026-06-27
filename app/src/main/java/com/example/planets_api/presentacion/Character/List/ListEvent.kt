package com.example.planets_api.presentacion.Character.List
sealed interface ListEvent {

    data class UpdateFilters(
        val name: String,
        val gender: String,
        val race: String
    ) : ListEvent

    data object Search : ListEvent
}