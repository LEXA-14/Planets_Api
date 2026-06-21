package com.example.planets_api.planets.presentacion.Planets.listPlanet

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.planets_api.planets.data.remote.Resource
import com.example.planets_api.planets.domain.planets.usecase.GetPlanetUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class ListViewModel @Inject constructor(
    private val getPlanetUseCase: GetPlanetUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(listUiState())
    val state = _state.asStateFlow()

    init {
        loadCharacters()
    }

    fun onEvent(event: listEvent) {
        when (event) {
            is listEvent.UpdateFilters -> _state.update {
                it.copy(
                    filterName = event.name,
                    filterIsDestroyed = event.isDestroyed

                )
            }

            listEvent.Search -> loadCharacters()
        }
    }

    private fun loadCharacters() {
        viewModelScope.launch {
            val current = _state.value
            _state.update { it.copy(isLoading = true) }

            android.util.Log.d("FILTRO_DEBUG", "name=${current.filterName}, isDestroyed=${current.filterIsDestroyed}")

            val result= getPlanetUseCase(
                name = current.filterName.takeIf { it.isNotBlank() },
                isDestroyed = current.filterIsDestroyed
            )

                when (result) {
                    is Resource.Loading -> _state.update { it.copy(isLoading = true) }

                    is Resource.Success ->
                        _state.update {
                            it.copy(
                                isLoading = false,
                                planets = result.data?: emptyList()
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
