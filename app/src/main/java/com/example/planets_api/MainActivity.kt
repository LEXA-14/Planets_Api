package com.example.planets_api

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.planets_api.Navegacion.MainNavigationDisplay
import com.example.planets_api.ui.theme.Planets_ApiTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Planets_ApiTheme {

                MainNavigationDisplay()

                }
            }
        }
    }


