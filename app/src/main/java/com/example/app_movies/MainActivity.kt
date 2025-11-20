package com.example.app_movies

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.*
import com.example.app_movies.ui.home.InicioScreen // 👈 Importación de tu pantalla de Inicio
import com.example.app_movies.ui.login.LoginScreen
import com.example.app_movies.ui.register.RegisterScreen
import com.example.app_movies.ui.theme.AppMoviesTheme

// 1. 🚀 CLAVE: Clase Sellada (Sealed Class) para manejar estados con o sin datos
//    El estado 'Home' ahora puede llevar el mensaje.
sealed class ScreenState {
    data object Login : ScreenState()
    data object Register : ScreenState()
    // El mensajeBienvenida es opcional (null) si se navega a Home desde otro lugar
    data class Home(val mensajeBienvenida: String? = null) : ScreenState()
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            AppMoviesTheme {
                // 2. **Estado de Navegación:** Usa la nueva clase sellada
                var currentScreen by remember { mutableStateOf<ScreenState>(ScreenState.Login) }

                // 3. **Lógica de Navegación:** Usa 'when' para manejar los diferentes estados
                when (val screen = currentScreen) { // Usamos 'val screen' para acceder a los datos

                    // --- Caso 1: Login ---
                    ScreenState.Login -> {
                        LoginScreen(
                            onNavigateToRegister = {
                                currentScreen = ScreenState.Register // Navega a Registro
                            }
                        )
                    }

                    // --- Caso 2: Register ---
                    ScreenState.Register -> {
                        RegisterScreen(
                            onNavigateToLogin = {
                                currentScreen = ScreenState.Login // Navega de vuelta a Login
                            },
                            // 🚀 CLAVE: Implementación de la nueva acción
                            onNavigateToHomeWithMessage = { mensaje ->
                                // Navega a Home y adjunta el mensaje de éxito al estado
                                currentScreen = ScreenState.Home(mensajeBienvenida = mensaje)
                            }
                        )
                    }

                    // --- Caso 3: Inicio (Recibiendo el mensaje) ---
                    is ScreenState.Home -> {
                        InicioScreen(
                            // Pasa el mensaje que viene adjunto en el estado de la pantalla
                            mensajeBienvenida = screen.mensajeBienvenida,
                            // Acción de navegación de regreso (o cualquier otra acción que necesite Inicio)
                            onNavigateBackToLogin = {
                                currentScreen = ScreenState.Login
                            }
                        )
                    }
                }
            }
        }
    }
}