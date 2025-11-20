package com.example.app_movies.ui.register

import android.app.DatePickerDialog
import android.widget.DatePicker
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.CalendarToday // Ícono de calendario
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.app_movies.R
import java.util.* // Para manejo de fechas

private val Icons.Filled.CalendarToday: Any

@Composable
fun RegisterScreen(
    onNavigateToLogin: () -> Unit,
    onNavigateToHomeWithMessage: (String) -> Unit
) {
    // --- VARIABLES DE ESTADO ---
    var usuario by remember { mutableStateOf("") }
    var contrasena by remember { mutableStateOf("") }
    var nombre by remember { mutableStateOf("") }
    var correoElectronico by remember { mutableStateOf("") }
    var fechaNacimiento by remember { mutableStateOf("") }
    var telefono by remember { mutableStateOf("") }

    // Estado del Dropdown de Género
    val generos = listOf("Hombre", "Mujer", "Otro", "Prefiero no decir")
    var genero by remember { mutableStateOf(generos[0]) }
    var expanded by remember { mutableStateOf(false) }

    // Estados de Error
    var telefonoError by remember { mutableStateOf<String?>(null) }
    var camposIncompletosError by remember { mutableStateOf<String?>(null) }

    // --- LÓGICA DEL DATE PICKER ---
    val context = LocalContext.current
    val calendar = Calendar.getInstance()
    val year = calendar.get(Calendar.YEAR)
    val month = calendar.get(Calendar.MONTH)
    val day = calendar.get(Calendar.DAY_OF_MONTH)

    val datePickerDialog = DatePickerDialog(
        context,
        { _: DatePicker, selectedYear: Int, selectedMonth: Int, selectedDay: Int ->
            fechaNacimiento = "${selectedDay}/${selectedMonth + 1}/${selectedYear}"
        }, year, month, day
    )
    datePickerDialog.datePicker.maxDate = System.currentTimeMillis() // No permite fechas futuras
    // ----------------------------


    // --- ESTILOS DE COMPONENTES ---
    val inputColors = OutlinedTextFieldDefaults.colors(
        focusedBorderColor = Color.Red,
        unfocusedBorderColor = Color.Black,
        focusedLabelColor = Color.Red,
        unfocusedLabelColor = Color.Black,
        focusedTextColor = Color.Black,
        unfocusedTextColor = Color.Black,
        cursorColor = Color.Red,
        errorBorderColor = Color.Red,
        errorLabelColor = Color.Red
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
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
        Text(text = "Registro", color = Color.Black)
        Spacer(modifier = Modifier.height(24.dp))


        // --- SECCIÓN 2: CAMPOS DE TEXTO ---

        // Nombre, Usuario, Contraseña, Correo (Estándar)
        OutlinedTextField(value = nombre, onValueChange = { nombre = it }, label = { Text("Nombre") }, textStyle = TextStyle(color = Color.Black), colors = inputColors, modifier = Modifier.fillMaxWidth())
        Spacer(modifier = Modifier.height(5.dp))
        OutlinedTextField(value = usuario, onValueChange = { usuario = it }, label = { Text("Usuario") }, textStyle = TextStyle(color = Color.Black), colors = inputColors, modifier = Modifier.fillMaxWidth())
        Spacer(modifier = Modifier.height(5.dp))
        OutlinedTextField(value = contrasena, onValueChange = {contrasena = it }, label = { Text("Contraseña") }, textStyle = TextStyle(color = Color.Black), colors = inputColors, modifier = Modifier.fillMaxWidth())
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(value = correoElectronico, onValueChange = {correoElectronico = it }, label = { Text("Correo electrónico") }, textStyle = TextStyle(color = Color.Black), colors = inputColors, modifier = Modifier.fillMaxWidth())
        Spacer(modifier = Modifier.height(8.dp))


        // 💡 CAMPO DE FECHA (DatePicker Dialog)
        OutlinedTextField(
            value = fechaNacimiento,
            onValueChange = { /* Solo lectura */ },
            label = { Text("Fecha de Nacimiento (DD/MM/YYYY)") },
            textStyle = TextStyle(color = Color.Black),
            colors = inputColors,
            readOnly = true,
            trailingIcon = {
                Icon(
                    Icons.Default.CalendarToday,
                    contentDescription = "Seleccionar Fecha",
                    modifier = Modifier.clickable { datePickerDialog.show() }
                )
            },
            modifier = Modifier
                .fillMaxWidth()
                .clickable { datePickerDialog.show() }
        )
        Spacer(modifier = Modifier.height(8.dp))


        // 💡 CAMPO DE TELÉFONO (Validación de 10 dígitos numéricos)
        OutlinedTextField(
            value = telefono,
            onValueChange = { newValue ->
                // Lógica de control de entrada (solo dígitos y max 10)
                if (newValue.length <= 10 && newValue.all { it.isDigit() }) {
                    telefono = newValue
                    telefonoError = null
                } else if (newValue.length > 10) {
                    telefonoError = "Máximo 10 dígitos"
                }
            },
            label = { Text("Teléfono (10 dígitos)") },
            textStyle = TextStyle(color = Color.Black),
            colors = inputColors,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            isError = telefonoError != null,
            supportingText = {
                if (telefonoError != null) {
                    Text(text = telefonoError!!, color = Color.Red)
                }
            },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))

        // 💡 CAMPO DE GÉNERO (Dropdown Menu)
        Box(modifier = Modifier.fillMaxWidth()) {
            OutlinedTextField(
                value = genero,
                onValueChange = { },
                label = { Text("Género") },
                textStyle = TextStyle(color = Color.Black),
                colors = inputColors,
                readOnly = true,
                trailingIcon = {
                    Icon(Icons.Filled.ArrowDropDown, contentDescription = "Mostrar menú", Modifier.clickable { expanded = true })
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { expanded = true }
            )
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
                modifier = Modifier.fillMaxWidth(0.9f)
            ) {
                generos.forEach { selection ->
                    DropdownMenuItem(
                        text = { Text(selection) },
                        onClick = {
                            genero = selection
                            expanded = false
                        }
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(16.dp))

        // Mensaje de Error General
        if (camposIncompletosError != null) {
            Text(text = camposIncompletosError!!, color = Color.Red, modifier = Modifier.padding(bottom = 8.dp))
        }

        // --- SECCIÓN 3: BOTONES (Validación final antes de navegar) ---
        Button(
            onClick = {
                // Limpieza de errores
                telefonoError = null
                camposIncompletosError = null

                // Definición de obligatorios (ajusta si otros campos deben ser obligatorios)
                val camposCompletos = nombre.isNotBlank() && usuario.isNotBlank() && contrasena.isNotBlank() && telefono.isNotBlank()
                val telefonoValido = telefono.length == 10 // Ya sabemos que es solo numérico

                // Revisa si el teléfono tiene 10 dígitos si está lleno
                if (telefono.isNotBlank() && telefono.length != 10) {
                    telefonoError = "El teléfono debe tener exactamente 10 dígitos."
                }

                if (camposCompletos && telefonoValido) {
                    // 🚀 Éxito: Navegar a Home con el mensaje
                    val mensajeDeExito = "¡Registro creado correctamente!"
                    onNavigateToHomeWithMessage(mensajeDeExito)
                } else {
                    // Error: Mostrar mensaje general
                    if (!camposCompletos) {
                        camposIncompletosError = "Por favor, completa todos los campos obligatorios."
                    }
                }
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

        TextButton(
            onClick = onNavigateToLogin
        ) {
            Text("¿Ya tienes una cuenta? Inicia Sesión", color = Color.Black)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RegisterScreenPreview() {
    RegisterScreen(
        onNavigateToLogin = {},
        onNavigateToHomeWithMessage = {}
    )
}