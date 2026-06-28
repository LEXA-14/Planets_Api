package com.example.planets_api.data.remote

import com.example.planets_api.domain.planets.model.Planets

data class PlanetsResponseDto(
    val items: List<PlanetDto>
)

data class PlanetDto(
    val id: Int,
    val name: String,
    val isDestroyed: Boolean,
    val description:String,
    val image: String
) {
    fun toDomain() = Planets(
        id,name, isDestroyed, description,image
    )
}