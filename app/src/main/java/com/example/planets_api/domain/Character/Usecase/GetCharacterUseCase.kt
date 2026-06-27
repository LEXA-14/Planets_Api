package com.example.planets_api.domain.Character.Usecase

import com.example.planets_api.domain.Character.Repository.CharacterRepository
import javax.inject.Inject

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