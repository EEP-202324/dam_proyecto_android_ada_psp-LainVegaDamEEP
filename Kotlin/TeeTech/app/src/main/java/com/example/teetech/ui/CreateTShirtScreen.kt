package com.example.teetech.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.teetech.model.TShirt
import com.example.teetech.viewmodel.TShirtViewModel

@Composable
fun CreateTShirtScreen(navController: NavController) {
    val backgroundBrush = Brush.verticalGradient(
        colors = listOf(Color.White, Color.LightGray),
        startY = 0f,
        endY = 1000f
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(brush = backgroundBrush)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .verticalScroll(rememberScrollState())
        ) {
            var size by remember { mutableStateOf(OptionsProvider.sizes.first()) }
            var color by remember { mutableStateOf(OptionsProvider.colors.first()) }
            var sleeve by remember { mutableStateOf(OptionsProvider.sleeves.first()) }
            var weight by remember { mutableStateOf("") }
            var gender by remember { mutableStateOf(OptionsProvider.genders.first()) }

            DropdownField("Talla", OptionsProvider.sizes, size, { size = it })
            DropdownField("Color", OptionsProvider.colors, color, { color = it })
            DropdownField("Manga", OptionsProvider.sleeves, sleeve, { sleeve = it })
            DropdownField("Género", OptionsProvider.genders, gender, { gender = it })

            OutlinedTextField(
                value = weight,
                onValueChange = { newWeight -> weight = newWeight },
                label = { Text("Gramaje") },
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White)
            )
            Button(
                onClick = {
                    val viewModel = TShirtViewModel()
                    viewModel.createTShirt(TShirt(0, size, color, sleeve, weight.toInt(), gender))
                    navController.popBackStack()  // Vuelve a la pantalla anterior después de crear la camiseta

                },
                modifier = Modifier.fillMaxWidth().padding(top = 16.dp)
            ) {
                Text("Crear Camiseta")
            }
        }
    }
}


@Composable
fun DropdownField(
    label: String,
    options: List<String>,
    selectedOption: String,
    onOptionSelected: (String) -> Unit,  // Cambio aquí para aceptar una función lambda
    modifier: Modifier = Modifier
) {
    var expanded by remember { mutableStateOf(false) }

    Column {
        OutlinedTextField(
            value = selectedOption,
            onValueChange = { },
            label = { Text(label) },
            readOnly = true,
            trailingIcon = {
                Icon(Icons.Filled.ArrowDropDown, contentDescription = "Expand", Modifier.clickable { expanded = !expanded })
            },
            modifier = modifier.fillMaxWidth()
        )
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = modifier.fillMaxWidth()
        ) {
            options.forEach { option ->
                DropdownMenuItem(
                    onClick = {
                        onOptionSelected(option)  // Usar el callback para actualizar el estado
                        expanded = false
                    },
                    text = { Text(option) }
                )
            }
        }
    }
}

