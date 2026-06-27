package com.example.planets_api.planets.data.remote

import com.example.planets_api.planets.domain.planets.model.Planets
import kotlinx.coroutines.flow.Flow


sealed class Resource<T>(
    val data: T? = null,
    val message: String? = null
) {
    class Success<T>(data: T) : Resource<T>(data)
    class Error<T>(message: String) : Resource<T>(null, message)
    class Loading<T> : Resource<T>()
}