package com.example.planets_api.di

import dagger.Module
import com.example.planets_api.data.remote.PlanetsApi
import com.example.planets_api.data.remote.remoteDataSource.PlanetDataSource
import com.example.planets_api.data.repository.PlanetRepositoryImpl
import com.example.planets_api.domain.planets.repository.PlanetRepository
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

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
            .baseUrl("https://dragonball-api.com/api/")
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            //.addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
            .build()
            .create(PlanetsApi::class.java)
    }



    @Provides
    @Singleton
    fun provideRepository(dataSource: PlanetDataSource): PlanetRepository {
        return PlanetRepositoryImpl(dataSource)
    }
}

