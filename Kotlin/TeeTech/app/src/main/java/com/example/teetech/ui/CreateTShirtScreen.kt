package com.example.teetech.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.teetech.model.TShirt
import com.example.teetech.viewmodel.TShirtViewModel

@Composable
fun CreateTShirtScreen(viewModel: TShirtViewModel) {
    var size by remember { mutableStateOf(OptionsProvider.sizes.first()) }
    var color by remember { mutableStateOf(OptionsProvider.colors.first()) }
    var sleeve by remember { mutableStateOf(OptionsProvider.sleeves.first()) }
    var weight by remember { mutableStateOf("") }
    var gender by remember { mutableStateOf(OptionsProvider.genders.first()) }

    Column(modifier = Modifier.padding(16.dp)) {
        DropdownField("Talla", OptionsProvider.sizes, size) { newSize -> size = newSize }
        DropdownField("Color", OptionsProvider.colors, color) { newColor -> color = newColor }
        DropdownField("Manga", OptionsProvider.sleeves, sleeve) { newSleeve -> sleeve = newSleeve }
        DropdownField("Género", OptionsProvider.genders, gender) { newGender -> gender = newGender }
        OutlinedTextField(
            value = weight,
            onValueChange = { newWeight -> weight = newWeight },
            label = { Text("Gramaje") }
        )
        Button(onClick = {
            viewModel.createTShirt(TShirt(0, size, color, sleeve, weight.toInt(), gender))
        }) {
            Text("Crear Camiseta")
        }
    }
}

@Composable
fun DropdownField(
    label: String,
    options: List<String>,
    selectedOption: String,
    onOptionSelected: (String) -> Unit
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
            }
        )
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            options.forEach { option ->
                DropdownMenuItem(
                    onClick = {
                        onOptionSelected(option)
                        expanded = false
                    },
                    text = { Text(option) }  // Corregido aquí
                )
            }
        }
    }
}
