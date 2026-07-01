package com.example.planets_api.presentacion.Planets.listPlanet

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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.planets_api.domain.planets.model.Planets
import coil.compose.AsyncImage


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListScreen(
    viewModel: ListViewModel = hiltViewModel(),
    onPlanetClick: (Int) -> Unit,
    onNavigateToCharacters:()-> Unit
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    ListBodyScreen(
        state = state,
        onEvent = viewModel::onEvent,
        onPlanetClick = onPlanetClick,
        onNavigateToCharacters=onNavigateToCharacters


    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListBodyScreen(
    state: ListUiState,
    onEvent: (listEvent) -> Unit,
    onPlanetClick: (Int) -> Unit,
    onNavigateToCharacters: () -> Unit,
) {
    var menuExpanded by remember { mutableStateOf(false) }
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Lista de Planetas") },
                actions = {
                    IconButton(onClick = { menuExpanded = true }) {
                        Icon(Icons.Default.MoreVert, contentDescription = "Menu")
                    }
                DropdownMenu(
                        expanded= menuExpanded,
                        onDismissRequest={menuExpanded=false}
                    ){
                        DropdownMenuItem(
                            text = {Text("Ver Characters")},
                            onClick = {
                                menuExpanded=false
                                onNavigateToCharacters()
                            }
                        )
                }
                }
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


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FilterSection(
    name: String,
    isDestroyed: Boolean?,

    onEvent: (listEvent) -> Unit,
) {
    var expanded by remember { mutableStateOf(false) }
    val opciones = listOf(
        "Selecciona" to null,
        "Destroyed" to true,
        "Not Destroyed" to false
    )
    val textoSeleccionado = opciones.firstOrNull { it.second == isDestroyed }?.first ?: "Selecciona"

    ElevatedCard(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth(),
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {

            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                OutlinedTextField(
                    value = name,
                    onValueChange = { onEvent(listEvent.UpdateFilters(name=it, isDestroyed=isDestroyed, )) },
                    label = { Text("Nombre") },
                    modifier = Modifier.weight(1f)
                )

                ExposedDropdownMenuBox(
                    expanded = expanded,
                    onExpandedChange = { expanded = it },
                    modifier= Modifier.weight(1f)
                ) {
                    OutlinedTextField(
                        value = textoSeleccionado,
                        onValueChange = {},
                        readOnly = true,
                        label = { Text("¿Destruido?") },
                        trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded) },
                        modifier = Modifier
                            .menuAnchor()
                            .fillMaxWidth()
                    )

                    ExposedDropdownMenu(
                        expanded = expanded,
                        onDismissRequest = { expanded = false }
                    ) {
                        opciones.forEach { (texto, valor) ->
                            DropdownMenuItem(
                                text = { Text(texto) },
                                onClick = {
                                    onEvent(listEvent.UpdateFilters(name = name, isDestroyed = valor))
                                    expanded = false
                                }
                            )
                        }
                    }
                }
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

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun ListBodyScreenPreview() {
    val samplePlanets = listOf(
        Planets(
            id = 1,
            name = "Vegeta",
            isDestroyed = true,
            description = "Planeta natal de la raza Saiyajin.",
            image = "https://dragonball-api.com/planets/Vegeta.webp"
        ),
        Planets(
            id = 2,
            name = "Namek",
            isDestroyed = false,
            description = "Planeta natal de los Namekianos, con cielo verde.",
            image = "https://dragonball-api.com/planets/Namek.webp"
        ),
        Planets(
            id = 3,
            name = "Tierra",
            isDestroyed = false,
            description = "Planeta hogar de los humanos y guerreros Z.",
            image = "https://dragonball-api.com/planets/Earth.webp"
        )
    )

    ListBodyScreen(
        state = ListUiState(
            planets = samplePlanets,
            isLoading = false,
            filterName = "",
            filterIsDestroyed = null
        ),
        onEvent = {},
        onPlanetClick = {},
        onNavigateToCharacters = {}
    )
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
            AsyncImage(
                model=planet.image,
                contentDescription=planet.name,
                modifier= Modifier.size(64.dp)
            )

            Spacer(modifier = Modifier.width(16.dp))

            Column {

                Text(planet.name)

                Text("${planet.isDestroyed} • ")
            }
        }
    }
}
