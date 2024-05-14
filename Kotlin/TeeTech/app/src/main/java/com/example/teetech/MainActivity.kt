package com.example.teetech

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.teetech.model.TShirt
import com.example.teetech.ui.theme.TeeTechTheme
import com.example.teetech.viewmodel.TShirtViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TeeTechTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    TShirtScreen()
                }
            }
        }
    }
}

@Composable
fun TShirtScreen() {
    val viewModel: TShirtViewModel = viewModel()
    val tShirts = viewModel.tShirts.collectAsState().value

    LaunchedEffect(key1 = Unit) {
        viewModel.loadTShirts()
    }

    LazyColumn {
        items(tShirts) { index ->
            TShirtItem(index)
        }

    }
    

//   TShirtList(tShirts = tShirts)
}

@Composable
fun TShirtItem(tShirt: TShirt) {
    Card {
        Column {
             Text(tShirt.size)
             Text(tShirt.color)
             Text(tShirt.sleeve)
             Text(tShirt.weight.toString())
        }

    }

}
//
//@Composable
//fun TShirtList(tShirts: Unit) {
//    Column {
//        viewModel.loadTShirts()
//    }
//}
//
//@Composable
//fun TShirtItem(tShirt: Int) {
//    Column {
//        viewModel.loadTShirts()
//    }
//}

//@Preview(showBackground = true)
//@Composable
//fun PreviewTShirtList() {
//    TShirtList(listOf(TShirt(1, "M", "Red", "Short", 150)))
//}

@Preview(showBackground = true)
@Composable
fun PreviewTShirtItem() {
    TShirtItem(TShirt(1, "M", "Red", "Short", 150))
}

