package com.example.planets_api.presentacion.Character.Detail

import com.example.planets_api.domain.Character.model.Character
import com.example.planets_api.domain.planets.model.Planets

data class CharacterDetailState (

    val isLoading: Boolean = false,
    val character: Character? = null,
    val error: String? = null
    )
