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
import com.example.teetech.model.TShirt
import com.example.teetech.viewmodel.TShirtViewModel

@Composable
fun CreateTShirtScreen(viewModel: TShirtViewModel) {
    val backgroundBrush = Brush.verticalGradient( //fondo con gradiente de blanco a gris medio
        colors = listOf(Color.White, Color.LightGray),
        startY = 0f,
        endY = 1000f
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            //fondo con gradiente de blanco a gris medio
            .background(brush = backgroundBrush)


    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .verticalScroll(rememberScrollState())  // Habilita el scroll si los elementos son muchos
        ) {
            var size by remember { mutableStateOf(OptionsProvider.sizes.first()) }
            var color by remember { mutableStateOf(OptionsProvider.colors.first()) }
            var sleeve by remember { mutableStateOf(OptionsProvider.sleeves.first()) }
            var weight by remember { mutableStateOf("") }
            var gender by remember { mutableStateOf(OptionsProvider.genders.first()) }

            // Asegurándonos de que cada campo tiene un fondo blanco
            DropdownField("Talla", OptionsProvider.sizes, size, Modifier.background(Color.White))
            DropdownField("Color", OptionsProvider.colors, color, Modifier.background(Color.White))
            DropdownField("Manga", OptionsProvider.sleeves, sleeve, Modifier.background(Color.White))
            DropdownField("Género", OptionsProvider.genders, gender, Modifier.background(Color.White))
            OutlinedTextField(
                value = weight,
                onValueChange = { newWeight -> weight = newWeight },
                label = { Text("Gramaje") },
                modifier = Modifier.fillMaxWidth().background(Color.White)  // Campo de texto ocupando todo el ancho con fondo blanco
            )
            Button(
                onClick = {
                    viewModel.createTShirt(TShirt(0, size, color, sleeve, weight.toInt(), gender))
                },
                modifier = Modifier.fillMaxWidth().padding(top = 16.dp)  // Botón ocupando todo el ancho y con padding superior
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
    onOptionSelected: Modifier,
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
            modifier = modifier.fillMaxWidth()  // Asegura que el dropdown ocupe todo el ancho
        )
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = modifier.fillMaxWidth()  // Asegura que el menú desplegable ocupe todo el ancho
        ) {
            options.forEach { option ->
                DropdownMenuItem(
                    onClick = {
                        onOptionSelected(option)
                        expanded = false
                    },
                    text = { Text(option) }  // Texto de cada opción en el menú
                )
            }
        }
    }
}

fun onOptionSelected(option: String) {

}
