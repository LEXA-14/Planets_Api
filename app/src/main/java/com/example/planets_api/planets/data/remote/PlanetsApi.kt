package com.example.planets_api.planets.data.remote

import retrofit2.http.Query
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface PlanetsApi {

    @GET("planets")
    suspend fun getPlanets(
        @Query("page") page: Int,
        @Query("limit") limit: Int,
        @Query("name") name: String?,
        @Query("isDestroyed") isDestroyed: Boolean?

    ): Response<PlanetsResponseDto>

    @GET("planets/{id}")
    suspend fun getPlanetDetail(
        @Path("id") id: Int
    ): Response<PlanetDto>
}