package com.example.planets_api.domain.Character.Usecase

import com.example.planets_api.data.remote.Resource
import com.example.planets_api.domain.Character.Repository.CharacterRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import com.example.planets_api.domain.Character.model.Character

class GetCharacterUseCase @Inject constructor(
    private val repository: CharacterRepository
){
    suspend operator fun invoke(
        page:Int=1,
        limit:Int=10,
        name:String?=null,
        gender:String?=null,
        race:String?=null,
    )=repository.getCharacters(page,limit,name,gender,race)
}

class GetCharacterDetailUseCase @Inject constructor(
    private val repository: CharacterRepository
) {
    suspend operator fun invoke(id: Int): Flow<Resource<Character>> {
        return repository.getCharacterDetail(id)
    }
}