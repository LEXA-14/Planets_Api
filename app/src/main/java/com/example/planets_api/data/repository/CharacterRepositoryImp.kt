package com.example.planets_api.data.repository

import com.example.planets_api.data.remote.Resource
import com.example.planets_api.data.remote.remoteDataSource.CharacterRemoteDataSource
import com.example.planets_api.data.remote.remoteDataSource.PlanetDataSource
import com.example.planets_api.domain.Character.Repository.CharacterRepository
import com.example.planets_api.domain.Character.model.Character

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CharacterRepositoryImp @Inject constructor(
    private val remoteDataSource: CharacterRemoteDataSource
): CharacterRepository {
    override suspend fun getCharacters(
        page: Int,
        limit: Int,
        name: String?,
        gender: String?,
        race: String?
    ): Flow<Resource<List<Character>>> = flow{
      emit(Resource.Loading())

        val response=remoteDataSource.getCharacters(page,limit,name,gender,race)
        response.onSuccess { characters->
            emit(Resource.Success(characters.items.map { it.toDomain() }))
        }.onFailure {
            emit(Resource.Error(it.message ?: "Error desconocido."))
        }
    }


    override suspend fun getCharacterDetail(id: Int): Flow<Resource<Character>> = flow {
    emit(Resource.Loading())

        val response=remoteDataSource.getCharacterDetail(id)
        response.onSuccess { character->
            emit(Resource.Success(character.toDomain()))
        }.onFailure {
            emit(Resource.Error(it.message ?: "Error desconocido"))
        }
    }

}