package com.example.planets_api.presentacion.Character.List

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.planets_api.data.remote.Resource
import com.example.planets_api.domain.Character.Usecase.GetCharacterUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class ListViewModel @Inject constructor(
    private val getCharactersUseCase: GetCharacterUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(ListState())
    val state = _state.asStateFlow()

    init {
        loadCharacters()
    }

    fun onEvent(event: ListEvent) {
        when (event) {
            is ListEvent.UpdateFilters -> _state.update {
                it.copy(
                    filterName = event.name,
                    filterGender = event.gender,
                    filterRace = event.race
                )
            }

            ListEvent.Search -> loadCharacters()
        }
    }

    private fun loadCharacters() {
        viewModelScope.launch {
            val current = _state.value

            getCharactersUseCase().collect{
                result->
                when(result){

                    is Resource.Loading -> _state.update { it.copy(isLoading = true) }

                    is Resource.Success ->
                        _state.update {
                            it.copy(
                                isLoading = false,
                                characters = result.data ?: emptyList()
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
