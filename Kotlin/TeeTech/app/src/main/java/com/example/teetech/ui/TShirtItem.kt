import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.teetech.R
import com.example.teetech.model.TShirt
import com.example.teetech.viewmodel.TShirtViewModel

@Composable
fun TShirtItem(tShirt: TShirt, viewModel: TShirtViewModel) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth(),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_tshirt), // Asegúrate de que este es el correcto ID del recurso
                contentDescription = "Camiseta",
                tint = parseColor(tShirt.color), // El ícono se colorea según el color de la camiseta
                modifier = Modifier.size(48.dp)
            )
            Spacer(modifier = Modifier.width(16.dp)) // Espacio entre el ícono y el texto
            Column(modifier = Modifier.weight(1f)) {
                Text("Size: ${tShirt.size}", style = MaterialTheme.typography.bodyLarge)
                Text("Color: ${tShirt.color}", style = MaterialTheme.typography.bodyMedium)
                Text("Sleeve: ${tShirt.sleeve}", style = MaterialTheme.typography.bodyMedium)
                Text("Weight: ${tShirt.weight} grams", style = MaterialTheme.typography.bodySmall)
            }
            IconButton(onClick = { viewModel.deleteTShirt(tShirt.id) }) {
                Icon(Icons.Default.Delete, contentDescription = "Delete")
            }
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
