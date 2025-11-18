package com.example.app_movies.ui.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.OutlinedTextFieldDefaults // 💡 IMPORTACIÓN NECESARIA para personalizar el OutlinedTextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.app_movies.R

@Composable
fun LoginScreen(
    onNavigateToRegister: () -> Unit // Función de navegación
) {

    var usuario by remember { mutableStateOf("") }
    var contrasena by remember { mutableStateOf("") }

    // 1. CONFIGURACIÓN DE COLOR PARA LOS CAMPOS DE TEXTO
    // Énfasis en Rojo (al enfocar) y Negro (por defecto)
    val inputColors = OutlinedTextFieldDefaults.colors(
        focusedBorderColor = Color.Red,
        unfocusedBorderColor = Color.Black,
        focusedLabelColor = Color.Red,
        unfocusedLabelColor = Color.Black,
        focusedTextColor = Color.Black,
        unfocusedTextColor = Color.Black,
        cursorColor = Color.Red
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            // 2. FONDO: Aplicamos el color Blanco
            .background(Color.White)
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.login),
            contentDescription = "Logo de la aplicación",
            modifier = Modifier.size(150.dp)
        )

        Spacer(modifier = Modifier.height(48.dp))

        // 3. CAMPO DE USUARIO
        OutlinedTextField(
            value = usuario,
            onValueChange = { usuario = it },
            label = { Text("Usuario") },
            colors = inputColors, // Aplicamos la configuración de color
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        // 4. CAMPO DE CONTRASEÑA
        OutlinedTextField(
            value = contrasena,
            onValueChange = { contrasena = it },
            label = { Text("Contraseña") },
            colors = inputColors, // Aplicamos la configuración de color
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(24.dp))

        // 5. BOTÓN INICIAR SESIÓN (Negro/Blanco)
        Button(
            onClick = {
                println("Iniciando sesión con: $usuario / $contrasena")
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Black,
                contentColor = Color.White
            ),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Iniciar Sesión")
        }

        Spacer(modifier = Modifier.height(16.dp))

        // 6. BOTÓN REGISTRAR (Negro/Blanco)
        Button(
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Black, // Fondo del botón en Negro
                contentColor = Color.White    // Texto (contenido) del botón en Blanco
            ),
            onClick = onNavigateToRegister,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Registrar")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    LoginScreen(onNavigateToRegister = {})
}