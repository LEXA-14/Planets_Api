package com.example.planets_api.presentacion.Planets.detailPlanets

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.planets_api.data.remote.Resource
import com.example.planets_api.domain.planets.usecase.GetPlanetDetailUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val getPlanetDetailUseCase: GetPlanetDetailUseCase,

) : ViewModel() {

    private val _state = MutableStateFlow(DetailState())
    val state = _state.asStateFlow()


    private fun loadPlanet(id: Int) {
        viewModelScope.launch {
            getPlanetDetailUseCase(id).collect { result ->
                when (result) {

                    is Resource.Loading -> _state.update { it.copy(isLoading = true) }

                    is Resource.Success -> _state.update {
                        it.copy(isLoading = false, planet = result.data)
                    }

                    is Resource.Error -> _state.update {
                        it.copy(isLoading = false, error = result.message)
                    }
                }
            }
        }
    }
}