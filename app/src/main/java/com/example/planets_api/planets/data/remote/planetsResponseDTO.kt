package com.example.planets_api.planets.data.remote

import com.example.planets_api.planets.domain.planets.model.Planets

data class PlanetsResponseDto(
    val items: List<PlanetDto>
)

data class PlanetDto(
    val id: Int,
    val name: String,
   val isDestroyed: Boolean
) {
    fun toDomain() = Planets(
        id,name, isDestroyed
    )
}