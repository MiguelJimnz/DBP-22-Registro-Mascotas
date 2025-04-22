package com.example.registromascotas.items

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest



@Composable
fun ScreenA(navController: NavController) {

    var textoName by remember { mutableStateOf("") }
    var textoRaza by remember { mutableStateOf("") }
    var textoTamaño by remember { mutableStateOf("") }
    var textoEdad by remember { mutableStateOf("") }
    var textoFoto by remember { mutableStateOf("") }

    Column(modifier = Modifier.padding(16.dp)) {
        Text(text = "Identificación Mascota")

        Spacer(modifier = Modifier.height(8.dp))

        // Nombre
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(text = "Nombre", modifier = Modifier.width(100.dp))
            TextField(
                value = textoName,
                onValueChange = { textoName = it },
                placeholder = { Text("Ej. Firulais") },
                modifier = Modifier.fillMaxWidth()
            )
        }

        // Raza
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(text = "Raza", modifier = Modifier.width(100.dp))
            TextField(
                value = textoRaza,
                onValueChange = { textoRaza = it },
                placeholder = { Text("Ej. Bulldog") },
                modifier = Modifier.fillMaxWidth()
            )
        }

        // Tamaño
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(text = "Tamaño", modifier = Modifier.width(100.dp))
            TextField(
                value = textoTamaño,
                onValueChange = { textoTamaño = it },
                placeholder = { Text("Ej. Grande") },
                modifier = Modifier.fillMaxWidth()
            )
        }

        // Edad
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(text = "Edad", modifier = Modifier.width(100.dp))
            TextField(
                value = textoEdad,
                onValueChange = { textoEdad = it },
                placeholder = { Text("Ej. 3 años") },
                modifier = Modifier.fillMaxWidth()
            )
        }

        // Foto (url)
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(text = "Foto", modifier = Modifier.width(100.dp))
            TextField(
                value = textoFoto,
                onValueChange = { textoFoto = it },
                placeholder = { Text("URL") },
                modifier = Modifier.fillMaxWidth()
            )

        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {

            },
            modifier = Modifier.align(Alignment.End)
        ) {
            Text(text = "Registrar")
        }
    }
}

