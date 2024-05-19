package com.example.teetech.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.navigation.NavController
import com.example.teetech.viewmodel.TShirtViewModel

@Composable
fun TShirtScreen(navController: NavController, viewModel: TShirtViewModel) {
    val tShirts by viewModel.tShirts.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.loadTShirts()  // Carga inicial y también se puede llamar después de crear/eliminar
    }

    Column {
        Button(onClick = { navController.navigate("createTShirt") }) {
            Text("Agregar nueva camiseta")
        }
        LazyColumn {
            items(tShirts) { tShirt ->
                TShirtItem(tShirt, viewModel)
            }
        }
    }
}
