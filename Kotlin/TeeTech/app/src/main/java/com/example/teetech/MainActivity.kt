package com.example.teetech

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.teetech.model.TShirt
import com.example.teetech.ui.CreateTShirtScreen
import com.example.teetech.ui.TShirtScreen
import com.example.teetech.ui.theme.TeeTechTheme
import com.example.teetech.viewmodel.TShirtViewModel


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TeeTechTheme {
                val navController = rememberNavController()
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    NavHost(navController = navController, startDestination = "tShirtList") {
                        composable("tShirtList") {
                            TShirtScreen(navController, viewModel())
                        }
                        composable("createTShirt") {
                            CreateTShirtScreen(navController, viewModel())
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
    val iconColor = parseColor(tShirt.color) // Color del ícono basado en el color de la camiseta

    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .clickable { /* Acción al hacer clic en la tarjeta */ },
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White) // Fondo blanco para todas las tarjetas
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = "Size: ${tShirt.size}",
                    style = MaterialTheme.typography.bodyLarge,
                    color = Color.Black // Texto en negro para contraste
                )
                Text(
                    text = "Color: ${tShirt.color}",
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.Black
                )
                Text(
                    text = "Sleeve: ${tShirt.sleeve}",
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.Black
                )
                Text(
                    text = "Weight: ${tShirt.weight} grams",
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.Black
                )
            }
            Icon(
                painter = painterResource(id = R.drawable.ic_tshirt), // Asegúrate de que este es el correcto ID del recurso
                contentDescription = "Camiseta",
                tint = iconColor, // El ícono se colorea según el color de la camiseta
                modifier = Modifier.size(48.dp)
            )
        }
    }
}

fun parseColor(colorName: String): Color {
    return when (colorName.toLowerCase()) {
        "red" -> Color.Red
        "black" -> Color.Black
        "blue" -> Color.Blue
        "green" -> Color.Green
        "yellow" -> Color.Yellow
        "pink" -> Color.Magenta
        "purple" -> Color(0xFF800080)
        else -> Color.LightGray // Un color por defecto si no coincide ninguno
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewTShirtItem() {
    TShirtItem(TShirt(1, "M", "Red", "Short", 150, "Unisex"))
}
