package com.example.planets_api.planets.data.repository

import com.example.planets_api.planets.data.remote.Resource
import com.example.planets_api.planets.data.remote.remoteDataSource.PlanetDataSource
import com.example.planets_api.planets.domain.planets.model.Planets
import com.example.planets_api.planets.domain.planets.repository.PlanetRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class PlanetRepositoryImpl @Inject constructor(
    private val remoteDataSource: PlanetDataSource
) : PlanetRepository {


    override suspend fun getPlanets(
        page: Int,
        limit: Int,
        name: String?,
        isDestroyed: Boolean?
    ): Resource<List<Planets>> {


        val response = remoteDataSource.getPlanet(page, limit, name,isDestroyed)
        return response.fold(
            onSuccess = { planets ->
            Resource.Success(planets.items.map { it.toDomain() })

        },
            onFailure= { Resource.Error(it.message ?: "Error desconocido")
        }
        )

    }

    override suspend fun getPlanetDetail(id: Int): Resource<Planets>{

        val response = remoteDataSource.getPlanetDetail(id)
        return  response.fold(
            onSuccess = { planets ->
            Resource.Success(planets.toDomain() )
        },
            onFailure ={
            Resource.Error(it.message ?: "Error desconocido")
        }
        )
    }
}