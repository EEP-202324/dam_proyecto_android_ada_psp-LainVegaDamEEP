package com.example.teetech

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.teetech.model.TShirt
import com.example.teetech.ui.theme.TeeTechTheme
import com.example.teetech.viewmodel.TShirtViewModel
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TeeTechTheme {
                val navController = rememberNavController()
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    NavHost(navController = navController, startDestination = "tShirtList") {
                        composable("tShirtList") {
                            TShirtScreen(navController)
                        }
                        composable("createTShirt") {
                            CreateTShirtScreen(viewModel())
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun TShirtScreen(navController: NavController) {
    val viewModel: TShirtViewModel = viewModel()
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

@Composable
fun TShirtItem(tShirt: TShirt) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .clickable { /* Handle card click */ },
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 4.dp
        )
    ) {
        Surface(color = MaterialTheme.colorScheme.surfaceVariant) {
            Column(
                modifier = Modifier
                    .background(MaterialTheme.colorScheme.background)
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Size: ${tShirt.size}",
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onBackground
                )
                Text(
                    text = "Color: ${tShirt.color}",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onBackground
                )
                Text(
                    text = "Sleeve: ${tShirt.sleeve}",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onBackground
                )
                Text(
                    text = "Weight: ${tShirt.weight} grams",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onBackground
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewTShirtItem() {
    TShirtItem(TShirt(1, "M", "Red", "Short", 150, "Unisex"))
}

@Composable
fun CreateTShirtScreen(viewModel: TShirtViewModel) {
    // Estado local para almacenar los valores del formulario
    var size by remember { mutableStateOf("") }
    var color by remember { mutableStateOf("") }
    var sleeve by remember { mutableStateOf("") }
    var weight by remember { mutableStateOf("") }
    var gender by remember { mutableStateOf("") }

    Column {
        TextField(value = size, onValueChange = { size = it }, label = { Text("Talla") })
        TextField(value = color, onValueChange = { color = it }, label = { Text("Color") })
        TextField(value = sleeve, onValueChange = { sleeve = it }, label = { Text("Manga") })
        TextField(value = weight, onValueChange = { weight = it }, label = { Text("Gramaje") })
        TextField(value = gender, onValueChange = {gender = it}, label = { Text("Género") })
        Button(onClick = {
            viewModel.createTShirt(TShirt(0, size, color, sleeve, weight.toInt(), gender))
        }) {
            Text("Crear Camiseta")
        }
    }
}
