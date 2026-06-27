package com.example.planets_api.presentacion.Character.List

data class ListState(
    val isLoading: Boolean = false,
     val characters: List<Character> = emptyList(),
     val error: String? = null,
     val filterName: String = "",
     val filterGender: String = "",
     val filterRace: String = ""

            )