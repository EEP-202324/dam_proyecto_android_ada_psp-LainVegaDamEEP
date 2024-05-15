package com.example.teetech.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.teetech.TShirtItem
import com.example.teetech.model.TShirt
import com.example.teetech.viewmodel.TShirtViewModel

@Composable
fun TShirtScreen(navController: NavController, viewModel: TShirtViewModel = viewModel()) {
    val tShirts = viewModel.tShirts.collectAsState().value

    LaunchedEffect(key1 = Unit) {
        viewModel.loadTShirts()
    }

    Column {
        Button(onClick = { navController.navigate("createTShirt") }) {
            Text("Agregar nueva camiseta")
        }
        LazyColumn {
            items(tShirts) { tShirt ->
                TShirtItem(tShirt)
            }
        }
    }
}
