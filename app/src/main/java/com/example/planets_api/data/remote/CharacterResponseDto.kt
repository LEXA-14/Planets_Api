package com.example.planets_api.data.remote

import com.example.planets_api.domain.Character.model.Character

data class CharacterResponseDto(
    val items:List<CharacterDto>
)

data class CharacterDto(
    val id: Int,
    val name: String,
    val ki: String,
    val race: String,
    val gender: String,
    val description: String,
    val image: String,
    val maxKi: String,
) {
    fun toDomain() = Character(
        id, name, ki, race, gender, description, image, maxKi,
    )
}
