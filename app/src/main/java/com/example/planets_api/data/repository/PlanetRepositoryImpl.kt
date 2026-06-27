package com.example.planets_api.data.repository

import com.example.planets_api.data.remote.Resource
import com.example.planets_api.data.remote.remoteDataSource.PlanetDataSource
import com.example.planets_api.domain.planets.model.Planets
import com.example.planets_api.domain.planets.repository.PlanetRepository
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
    ): Flow<Resource<List<Planets>>> = flow {

        emit(Resource.Loading())


        val response = remoteDataSource.getPlanet(page, limit, name, isDestroyed)
        response.fold(
            onSuccess = { planets ->
                emit(Resource.Success(planets.items.map { it.toDomain() }))

            },
            onFailure = {
                emit(Resource.Error(it.message ?: "Error desconocido"))
            }
        )

    }

    override suspend fun GetPlanetDetail(id: Int): Flow<Resource<Planets>> = flow {

        emit(Resource.Loading())


        val response = remoteDataSource.getPlanetDetail(id)

        response.fold(
            onSuccess = { planets ->
            emit(Resource.Success(planets.toDomain() ))
        },
            onFailure ={
           emit( Resource.Error(it.message ?: "Error desconocido"))
        }
        )
    }
}
