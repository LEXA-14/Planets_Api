package com.example.planets_api.domain.planets.repository

import com.example.planets_api.data.remote.Resource
import com.example.planets_api.domain.planets.model.Planets
import kotlinx.coroutines.flow.Flow


interface PlanetRepository {
    suspend fun getPlanets(
        page: Int ,
        limit: Int,
        name: String? = null,
        isDestroyed: Boolean? = null
    ): Flow<Resource<List<Planets>>>

    suspend fun GetPlanetDetail(id: Int): Flow<Resource<Planets>>
}