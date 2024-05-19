package com.example.teetech.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import kotlinx.coroutines.delay

@Composable
fun MainScreen(navController: NavController) {
    LaunchedEffect(key1 = true) {
        delay(2000)  // Espera 2 segundos antes de navegar
        navController.navigate("tShirtList") {  // Cambiar a tShirtList o la ruta correcta
            popUpTo("mainScreen") { inclusive = true }
        }
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "Bienvenido a TeeTech",
            style = MaterialTheme.typography.headlineMedium,
        )
    }
}
