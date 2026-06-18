package com.example.planets_api.planets.di

import com.example.planets_api.Planets_api
import com.example.planets_api.planets.data.remote.PlanetsApi
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Provides
import kotlinx.serialization.json.Json
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton


@Singleton
    @Provides
    fun provideMoshi(): Moshi {
        return Moshi
            .Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
    }

    @Provides
    @Singleton
    fun provideApi(moshi: Moshi): PlanetsApi {
        val json = Json { ignoreUnknownKeys = true }
        return Retrofit.Builder()
            .baseUrl("https://dragonball-api.com/api-docs")
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            //.addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
            .build()
            .create(PlanetsApi::class.java)
    }

//    @Provides
//    @Singleton
//    fun provideRepository(api: DragonBallApi): CharacterRepository {
//        return CharacterRepositoryImpl(api)
//    }
