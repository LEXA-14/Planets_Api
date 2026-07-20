package com.example.planets_api.domain.planets.usecase

import com.example.planets_api.domain.planets.repository.PlanetRepository
import javax.inject.Inject

class GetPlanetUseCase @Inject constructor(
    private val repository: PlanetRepository
) {
     suspend operator fun invoke(
        page: Int=1,
        limit:Int=10,
        name:String?=null,
        isDestroyed: Boolean?=null
    )= repository.getPlanets(page,limit,name,isDestroyed)
}

class GetPlanetDetailUseCase @Inject constructor(
    private val repository: PlanetRepository
) {
    suspend operator fun invoke(id: Int) = repository.GetPlanetDetail(id)
}