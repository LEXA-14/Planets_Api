package com.example.planets_api.presentacion.Planets.detailPlanets

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

import coil.compose.AsyncImage
import com.example.planets_api.domain.planets.model.Planets

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PlanetDetailScreen(
    planetId: Int,
    viewModel: DetailViewModel= hiltViewModel(),
    onBack: () -> Unit
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(planetId) {
        viewModel.loadPlanet(planetId)
    }

}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PlanetDetailBodyScreen(
    state: DetailState,
    onBack: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Detalle del Planeta") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.Default.ArrowBack, contentDescription = null)
                    }
                }
            )
        }
    ) { padding ->
        state.planet?.let { planet ->
            Column(
                modifier = Modifier
                    .padding(padding)
                    .padding(16.dp)
            ) {
                AsyncImage(
                    model=planet.image,
                    contentDescription=planet.name,
                    modifier= Modifier.fillMaxWidth()
                        .height(300.dp)
                )
                Text(planet.name)

                Text("Destruido: ${planet.isDestroyed}")
                Text("Descripcion: ${planet.description}")

            }
        }
    }

}
@Preview(showBackground = true)
@Composable
fun PlanetDetailScreenPreview() {
    val samplePlanet = Planets(
        PlanetId = 2,
        name = "Namek",
        isDestroyed = false,
        description = "Namek es el planeta natal de los Namekianos, incluyendo a Piccolo y Dende. Tiene tres soles y un cielo de color verde.",
        image = "https://dragonball-api.com/planets/Namek.webp"
    )

    PlanetDetailBodyScreen(
        state = DetailState(planet = samplePlanet),
        onBack = {}
    )
}
