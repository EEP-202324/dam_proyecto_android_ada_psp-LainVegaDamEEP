package com.example.teetech.network.com.example.teetech.ui.theme

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun HomeScreen() {
    Column(modifier = Modifier.padding(16.dp)) {
        // Botón para cargar datos
        Button(onClick = { /* Acción para cargar datos */ }) {
            Text("Load Data")
        }

        // Espacio entre botones
        Spacer(modifier = Modifier.height(8.dp))

        // Botón para crear datos
        Button(onClick = { /* Acción para crear datos */ }) {
            Text("Create Data")
        }

        // Espacio entre botones
        Spacer(modifier = Modifier.height(8.dp))

        // Botón para actualizar datos
        Button(onClick = { /* Acción para actualizar datos */ }) {
            Text("Update Data")
        }

        // Espacio entre botones
        Spacer(modifier = Modifier.height(8.dp))

        // Botón para eliminar datos
        Button(onClick = { /* Acción para eliminar datos */ }) {
            Text("Delete Data")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewHomeScreen() {
    HomeScreen()
}

