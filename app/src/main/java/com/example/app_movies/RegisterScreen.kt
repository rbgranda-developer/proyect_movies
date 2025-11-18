package com.example.app_movies.ui.register

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextFieldDefaults // 💡 Importación clave para personalizar OutlinedTextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.app_movies.R

@Composable
fun RegisterScreen(
    onNavigateToLogin: () -> Unit
) {
    // Variables de estado (sin cambios)
    var usuario by remember { mutableStateOf("") }
    var contrasena by remember { mutableStateOf("") }
    var nombre by remember {mutableStateOf("")}
    var correoElectronico by remember {mutableStateOf("")}
    var fechaNacimiento by remember {mutableStateOf("")}
    var telefono by remember {mutableStateOf("")}
    var genero by remember {mutableStateOf("")}

    // 1. CONFIGURACIÓN DE COLOR PARA LOS CAMPOS DE TEXTO (Paleta Rojo/Negro)
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
            // FONDO: Blanco
            .background(Color.White)
            .padding(10.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // --- SECCIÓN 1: CABECERA Y LOGO ---
        Image(
            painter = painterResource(id = R.drawable.login),
            contentDescription = "Logo de la aplicación",
            modifier = Modifier.size(120.dp).padding(vertical = 16.dp)
        )

        // Título: Color Negro
        Text(text = "Registro", color = Color.Black)

        Spacer(modifier = Modifier.height(24.dp))

        // --- SECCIÓN 2: CAMPOS DE TEXTO (Aplicando 'colors' a cada campo) ---

        OutlinedTextField(
            value = nombre,
            onValueChange = { nombre = it },
            label = { Text("Nombre") }, // Ya no necesitamos el color aquí, lo maneja `colors`
            textStyle = androidx.compose.ui.text.TextStyle(color = Color.Black),
            colors = inputColors, // 👈 APLICACIÓN DE COLORES
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(5.dp))

        OutlinedTextField(
            value = usuario,
            onValueChange = { usuario = it },
            label = { Text("Usuario") },
            textStyle = androidx.compose.ui.text.TextStyle(color = Color.Black),
            colors = inputColors, // 👈 APLICACIÓN DE COLORES
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(5.dp))

        OutlinedTextField(
            value = contrasena,
            onValueChange = {contrasena = it },
            label = { Text("Contraseña") },
            textStyle = androidx.compose.ui.text.TextStyle(color = Color.Black),
            colors = inputColors, // 👈 APLICACIÓN DE COLORES
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = correoElectronico,
            onValueChange = {correoElectronico = it },
            label = { Text("Correo electrónico") },
            textStyle = androidx.compose.ui.text.TextStyle(color = Color.Black),
            colors = inputColors, // 👈 APLICACIÓN DE COLORES
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = fechaNacimiento,
            onValueChange = {fechaNacimiento = it },
            label = { Text("Fecha de Nacimiento") },
            textStyle = androidx.compose.ui.text.TextStyle(color = Color.Black),
            colors = inputColors, // 👈 APLICACIÓN DE COLORES
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = telefono,
            onValueChange = {telefono = it },
            label = { Text("Teléfono") },
            textStyle = androidx.compose.ui.text.TextStyle(color = Color.Black),
            colors = inputColors, // 👈 APLICACIÓN DE COLORES
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = genero,
            onValueChange = {genero = it },
            label = { Text("Género") },
            textStyle = androidx.compose.ui.text.TextStyle(color = Color.Black),
            colors = inputColors, // 👈 APLICACIÓN DE COLORES
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))

        // --- SECCIÓN 3: BOTONES ---
        // Botón principal: Negro/Blanco
        Button(
            onClick = {
                println("Registrando...")
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Black,
                contentColor = Color.White
            ),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Registrarse")
        }

        Spacer(modifier = Modifier.height(5.dp))

        // Botón de Texto: Texto Rojo
        TextButton(
            onClick = onNavigateToLogin
        ) {
            Text("¿Ya tienes una cuenta? Inicia Sesión", color = Color.Black) // 👈 Texto Rojo
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RegisterScreenPreview() {
    RegisterScreen(onNavigateToLogin = {})
}