package com.example.planets_api.planets.domain.planets.repository

import com.example.planets_api.planets.data.remote.Resource
import com.example.planets_api.planets.domain.planets.model.Planets


interface PlanetRepository {
    suspend fun getPlanets(
        page: Int? = null,
        limit: Int? = null,
        name: String? = null,
        isDestroyed: Boolean? = null
    ): Resource<List<Planets>>

    suspend fun getPlanetDetail(id: Int): Resource<Planets>
}