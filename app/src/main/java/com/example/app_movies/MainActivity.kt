package com.example.app_movies

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
// Importa la pantalla de login desde el paquete 'ui.login'
import com.example.app_movies.ui.login.LoginScreen
import com.example.app_movies.ui.theme.AppMoviesTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            AppMoviesTheme {
                LoginScreen()
            }
        }
    }
}
