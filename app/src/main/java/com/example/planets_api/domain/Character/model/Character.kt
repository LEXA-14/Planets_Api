package com.example.planets_api.domain.Character.model

data class Character(
    val CharacterId: Int,
    val name: String,
    val ki: String,
    val race: String,
    val gender: String,
    val description: String,
    val image: String,
    val maxKi: String
)