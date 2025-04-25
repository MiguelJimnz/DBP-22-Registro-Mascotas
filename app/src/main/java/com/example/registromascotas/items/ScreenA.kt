package com.example.registromascotas.items

import Mascota
import MascotaViewModel
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.registromascotas.ui.theme.pastelBlue
import com.example.registromascotas.ui.theme.pastelPink
import com.example.registromascotas.ui.theme.pastelPurple
import com.example.registromascotas.ui.theme.pastelYellow
import java.net.URLEncoder
import java.nio.charset.StandardCharsets


@Composable
fun ScreenA(navController: NavController, viewModel: MascotaViewModel) {

    var textoName by remember { mutableStateOf("") }
    var textoRaza by remember { mutableStateOf("") }
    var textoTamaño by remember { mutableStateOf("") }
    var textoEdad by remember { mutableStateOf("") }
    var textoFoto by remember { mutableStateOf("") }







    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(pastelYellow) // Fondo claro
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text = "Identificación Mascota",
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold,
            color = pastelPurple
        )

        Spacer(modifier = Modifier.height(8.dp))



        @Composable
        fun textFields(label: String, value: String, onValueChange: (String) -> Unit) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = label,
                    modifier = Modifier.width(100.dp),
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
                TextField(
                    value = value,
                    onValueChange = onValueChange,
                    placeholder = { Text("Ej. $label") },
                    colors = TextFieldDefaults.colors(
                        unfocusedContainerColor = Color.White,
                        focusedContainerColor = pastelPink,
                        cursorColor = Color.Black
                    ),
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier.fillMaxWidth().border(BorderStroke(1.dp, pastelBlue))
                )
            }
        }


        textFields("Nombre", textoName) { textoName = it }
        textFields("Raza", textoRaza) { textoRaza = it }
        textFields("Tamaño", textoTamaño) { textoTamaño = it }
        textFields("Edad", textoEdad) { textoEdad = it }
        textFields("Foto", textoFoto) { textoFoto = it }

        Spacer(modifier = Modifier.height(16.dp))


        Button(
            onClick = {
                val encodedUrl = URLEncoder.encode(textoFoto, StandardCharsets.UTF_8.toString())

                viewModel.agregarMascota(
                    Mascota(
                        nombre = textoName,
                        raza = textoRaza,
                        tamaño = textoTamaño,
                        edad = textoEdad,
                        fotoUrl = textoFoto
                    )
                )
                navController.navigate("screenB")

            },
            modifier = Modifier.align(Alignment.End),
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.buttonColors(containerColor = pastelBlue)
        ) {
            Text(text = "Registrar", fontWeight = FontWeight.Bold, color = Color.Black)
        }
    }
}

