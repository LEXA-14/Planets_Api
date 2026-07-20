package com.example.planets_api.presentacion.Planets.listPlanet

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.planets_api.data.remote.Resource
import com.example.planets_api.domain.planets.usecase.GetPlanetUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class PlanetListViewModel @Inject constructor(
    private val getPlanetUseCase: GetPlanetUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(PlanetListUiState())
    val state = _state.asStateFlow()

    init {
        loadPlanets()
    }

    fun onEvent(event: listEvent) {
        when (event) {
            is listEvent.UpdateFilters -> _state.update {
                it.copy(
                    filterName = event.name,
                    filterIsDestroyed = event.isDestroyed

                )
            }

            listEvent.Search -> loadPlanets()
        }
    }

    private fun loadPlanets() {
        viewModelScope.launch {
            val current = _state.value
            getPlanetUseCase().collect { result ->
                when (result) {

                    is Resource.Loading -> _state.update { it.copy(isLoading = true) }

                    is Resource.Success ->
                        _state.update {
                            it.copy(
                                isLoading = false,
                                planets = result.data ?: emptyList()
                            )
                        }

                    is Resource.Error ->
                        _state.update {
                            it.copy(
                                isLoading = false,
                                error = result.message
                            )
                        }
                }
            }
        }
    }
}
