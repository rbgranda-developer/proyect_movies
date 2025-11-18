package com.example.app_movies

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.* // 👈 IMPORTANTE
// ... otras importaciones
import com.example.app_movies.ui.login.LoginScreen
import com.example.app_movies.ui.register.RegisterScreen
import com.example.app_movies.ui.theme.AppMoviesTheme

// Define las dos pantallas que tienes
enum class Screen {
    Login,
    Register
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            AppMoviesTheme {
                // 1. **Estado:** Esta variable rastrea la pantalla actual.
                var currentScreen by remember { mutableStateOf(Screen.Login) }

                // 2. **Lógica de Muestra:** Usa 'when' para decidir qué Composable mostrar.
                when (currentScreen) {
                    Screen.Login -> {
                        // Muestra la pantalla de Login. Cuando el usuario hace clic en el botón de
                        // Registrar, la acción (onNavigateToRegister) cambia el estado.
                        LoginScreen(
                            onNavigateToRegister = {
                                currentScreen = Screen.Register // CAMBIA EL ESTADO
                            }
                        )
                    }
                    Screen.Register -> {
                        // Muestra la pantalla de Registro. La acción (onNavigateToLogin)
                        // cambia el estado de vuelta.
                        RegisterScreen(
                            onNavigateToLogin = {
                                currentScreen = Screen.Login // CAMBIA EL ESTADO DE VUELTA
                            }
                        )
                    }
                }
            }
        }
    }
}