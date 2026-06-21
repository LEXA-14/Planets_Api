package com.example.planets_api.planets.presentacion.Planets.listPlanet

import androidx.compose.foundation.clickable
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.planets_api.planets.domain.planets.model.Planets
import com.example.planets_api.ui.theme.Planets_ApiTheme


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListScreen(
    viewModel: ListViewModel = hiltViewModel(),
    onPlanetClick: (Int) -> Unit
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    ListBodyScreen(
        state = state,
        onEvent = viewModel::onEvent,
        onPlanetClick = onPlanetClick
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListBodyScreen(
    state: listUiState,
    onEvent: (listEvent) -> Unit,
    onPlanetClick: (Int) -> Unit,
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Lista de Planetas") }
            )
        }
    ) { padding ->

        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
        ) {

            FilterSection(
                name = state.filterName,
                isDestroyed = state.filterIsDestroyed,

                onEvent = onEvent,
            )

            if (state.isLoading) {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
            }

            LazyColumn(
                contentPadding = PaddingValues(16.dp)
            ) {
                items(state.planets) { planet ->
                    PlanetItem(
                        onClick = { onPlanetClick(planet.id) },
                        planet = planet,

                    )
                    Spacer(modifier = Modifier.height(4.dp))
                }
            }
        }
    }
}


@Composable
fun FilterSection(
    name: String,
    isDestroyed:String,

    onEvent: (listEvent) -> Unit,
) {

    ElevatedCard(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth(),
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
//            OutlinedTextField(
//                value = name,
//                onValueChange = { onEvent(listEvent.UpdateFilters(name,isDestroyed)) },
//                label = { Text("Nombre (ej. Goku)") },
//                modifier = Modifier.fillMaxWidth()
//            )

            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                OutlinedTextField(
                    value = name,
                    onValueChange = { onEvent(listEvent.UpdateFilters(name, isDestroyed, )) },
                    label = { Text("Nombre") },
                    modifier = Modifier.weight(1f)
                )
                OutlinedTextField(
                    value = isDestroyed,
                    onValueChange = { onEvent(listEvent.UpdateFilters(name,isDestroyed, )) },
                    label = { Text("is Destroyed?") },
                    modifier = Modifier.weight(1f)
                )
            }

            Button(
                onClick = { onEvent(listEvent.Search) },
                modifier = Modifier.align(Alignment.End)
            ) {
                Text("Buscar")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ListBodyScreenPreview() {
    val samplePlanets = listOf(
        Planets(
            id = 1,
            name = "Marte",
            isDestroyed = false

        ),
       Planets(
            id = 2,
            name = "Vegeta",
           isDestroyed = true

        )
    )
    val state = listUiState(
       planets = samplePlanets,
        filterName = "Goku"
    )

    Planets_ApiTheme{
        Surface {
            ListBodyScreen(
                state = state,
                onEvent = {},
                onPlanetClick = {}
            )
        }
    }
}

@Composable
fun PlanetItem(
    planet: Planets,
    onClick: () -> Unit
) {
    ElevatedCard(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {



            Spacer(modifier = Modifier.width(16.dp))

            Column {
                Text(planet.name)
                Text("${planet.isDestroyed} • ")
            }
        }
    }
}
