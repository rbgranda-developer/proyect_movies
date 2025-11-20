package com.example.app_movies.ui.home // ⬅️ Revisa y ajusta este paquete si es necesario

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.* // Necesario para remember, LaunchedEffect, etc.
import androidx.compose.ui.Modifier
import kotlinx.coroutines.launch // Necesario para el scope.launch

@Composable
fun InicioScreen(
    // Recibe el mensaje que enviamos desde RegistroScreen a través de MainActivity
    mensajeBienvenida: String?,

    // Función de navegación para salir de esta pantalla (opcional)
    onNavigateBackToLogin: () -> Unit
) {
    // 1. Inicializar los estados necesarios para el Snackbar
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    Scaffold(
        // El SnackbarHost es donde aparecerá el mensaje
        snackbarHost = { SnackbarHost(snackbarHostState) },
        modifier = Modifier.fillMaxSize()
    ) { paddingValues ->

        // 2. 🚀 Lógica para mostrar el mensaje
        if (!mensajeBienvenida.isNullOrBlank()) {

            // LaunchedEffect: Se ejecuta una vez cuando la Composable entra a la pantalla
            // y cuando 'mensajeBienvenida' cambie (aunque en este caso solo ocurre una vez).
            LaunchedEffect(mensajeBienvenida) {
                scope.launch {
                    // Muestra el mensaje con la duración deseada
                    snackbarHostState.showSnackbar(
                        message = mensajeBienvenida,
                        actionLabel = "Aceptar",
                        duration = SnackbarDuration.Long
                    )
                }
            }
        }

        // 3. Contenido Principal de la Pantalla de Inicio
        Text(
            "¡Bienvenido! Esta es tu área de películas.",
            Modifier.padding(paddingValues)
        )
        // ... (Agrega aquí tu contenido de UI principal) ...
    }
}