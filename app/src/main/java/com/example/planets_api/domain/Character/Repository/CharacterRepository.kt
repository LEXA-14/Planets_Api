package com.example.planets_api.domain.Character.Repository

import com.example.planets_api.data.remote.Resource
import com.example.planets_api.domain.Character.model.Character
import kotlinx.coroutines.flow.Flow

interface CharacterRepository {
    suspend fun getCharacters(
        page:Int,
        limi:Int,
        name: String?,
        gender:String?,
        race:String?,
    ): Flow<Resource<List<Character>>>

    suspend fun getCharacterDetail(id: Int): Flow<Resource<Character>>
}