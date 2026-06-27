package com.example.planets_api.data.remote.remoteDataSource

import com.example.planets_api.data.remote.CharacterDto
import com.example.planets_api.data.remote.CharacterResponseDto
import com.example.planets_api.data.remote.PlanetDto
import com.example.planets_api.data.remote.PlanetsApi
import com.example.planets_api.data.remote.PlanetsResponseDto
import retrofit2.HttpException
import javax.inject.Inject

class PlanetDataSource @Inject constructor(
    private val api: PlanetsApi
) {

    suspend fun getPlanet(
        page: Int,
        limit: Int,
        name: String?,
        isDestroyed: Boolean?

    ): Result<PlanetsResponseDto> {
        try {
            val response = api.getPlanets(page, limit, name, isDestroyed)
            if (!response.isSuccessful) {
                return Result.failure(Exception("Error de red ${response.code()}"))
            }
            return Result.success(response.body()!!)
        } catch (e: HttpException) {
            return Result.failure(Exception("Error de servidor", e))
        } catch (e: Exception) {
            return Result.failure(Exception("Error desconocido", e))
        }
    }

    suspend fun getPlanetDetail(id: Int): Result<PlanetDto> {
        try {
            val response = api.getPlanetDetail(id)
            if (!response.isSuccessful) {
                return Result.failure(Exception("Error de red ${response.code()}"))
            }
            return Result.success(response.body()!!)
        } catch (e: HttpException) {
            return Result.failure(Exception("Error de servidor", e))
        } catch (e: Exception) {
            return Result.failure(Exception("Error desconocido", e))
        }
    }

    //Character

    suspend fun getCharacters(
        page: Int,
        limit: Int,
        name: String?,
        gender: String?,
        race: String?,
    ): Result<CharacterResponseDto> {
        try {
            val response = api.getCharacters(page, limit, name, gender, race)
            if (!response.isSuccessful) {
                return Result.failure(Exception("Error de red ${response.code()}"))
            }

            return Result.success(response.body()!!)
        } catch (e: HttpException) {
            return Result.failure(Exception("Error de servidor", e))
        } catch (e: Exception) {
            return Result.failure(Exception("Error desconocido", e))
        }
    }

    suspend fun getCharacterDetail(id: Int): Result<CharacterDto>{
        try {
            val response = api.getCharacterDetail(id)

            if (!response.isSuccessful) {
                return Result.failure(Exception("Error de red ${response.code()}"))
            }
            return Result.success(response.body()!!)
        }catch (e: HttpException){
            return Result.failure(Exception("Error de servidor", e))
        }catch (e: Exception){
            return Result.failure(Exception("Error desconocido",e))
        }
    }


}


