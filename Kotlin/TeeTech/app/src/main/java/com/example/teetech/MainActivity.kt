package com.example.teetech

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.teetech.ui.CreateTShirtScreen
import com.example.teetech.ui.MainScreen
import com.example.teetech.ui.SplashScreen
import com.example.teetech.ui.TShirtScreen
import com.example.teetech.ui.theme.TeeTechTheme
import com.example.teetech.viewmodel.TShirtViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TeeTechTheme {
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = "splashScreen") {
                    composable("splashScreen") { SplashScreen(navController) }
                    composable("mainScreen") { MainScreen(navController) }  // Añadir MainScreen aquí
                    composable("tShirtList") { TShirtScreen(navController, viewModel()) }
                    composable("createTShirt") { CreateTShirtScreen(navController) }
                }
            }
        }
    }
}
