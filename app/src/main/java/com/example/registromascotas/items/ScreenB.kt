package com.example.registromascotas.items

import Mascota
import MascotaViewModel
import android.R.attr.bottom
import android.R.attr.text
import android.R.attr.value
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.registromascotas.ui.theme.pastelBlue
import com.example.registromascotas.ui.theme.pastelPink
import com.example.registromascotas.ui.theme.pastelPurple
import com.example.registromascotas.ui.theme.pastelYellow
import java.net.URLDecoder
import java.nio.charset.StandardCharsets


@Composable
fun ScreenB(
    navController: NavController,
    viewModel: MascotaViewModel


) {
    var showDialog by remember { mutableStateOf(false) }
    var indexToDelete by remember { mutableStateOf(-1) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(pastelYellow)
            .padding(16.dp)
    ) {
        Text(
            text = "Agenda de Mascotas",
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold,
            color = pastelPurple,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        LazyColumn(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            itemsIndexed(viewModel.listaMascotas) { index, mascota ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth(),
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    elevation = CardDefaults.cardElevation(4.dp),
                    shape = RoundedCornerShape(16.dp)
                ) {
                    Column(
                        modifier = Modifier.padding(12.dp)
                    ) {Row(modifier = Modifier.fillMaxWidth()) {
                        AsyncImage(
                            model = mascota.fotoUrl,
                            contentDescription = null,
                            modifier = Modifier
                                .size(100.dp)
                                .clip(RoundedCornerShape(12.dp)),
                            contentScale = ContentScale.Crop
                        )
                        Spacer(modifier = Modifier.width(12.dp))
                        Column {
                            Text("🐶 Nombre: ${mascota.nombre}", fontWeight = FontWeight.Bold)
                            Text("🐾 Raza: ${mascota.raza}")
                            Text("📏 Tamaño: ${mascota.tamaño}")
                            Text("🎂 Edad: ${mascota.edad}")
                        }
                    }

                        Row(
                            horizontalArrangement = Arrangement.SpaceBetween,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Button(
                                onClick = {
                                    indexToDelete = index
                                    showDialog = true
                                },
                                colors = ButtonDefaults.buttonColors(containerColor = Color.Red)
                            ) {
                                Text("Eliminar", color = Color.White)
                            }

                            Button(
                                onClick = {
                                    navController.navigate("screenEdit/$index")
                                },
                                modifier = Modifier.padding(4.dp)
                            ) {
                                Text("Editar")
                            }
                        }
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = { navController.navigate("screenA") },
            modifier = Modifier.align(Alignment.CenterHorizontally),
            colors = ButtonDefaults.buttonColors(containerColor = pastelPink)
        ) {
            Text("Volver a registrar", fontWeight = FontWeight.Bold)
        }

        // Diálogo de confirmación
        if (showDialog && indexToDelete != -1) {
            AlertDialog(
                onDismissRequest = {
                    showDialog = false
                    indexToDelete = -1
                },
                title = { Text("¿Eliminar mascota?", fontWeight = FontWeight.Bold) },
                text = { Text("¿Estás seguro de que deseas eliminar esta mascota? Esta acción no se puede deshacer.") },
                confirmButton = {
                    TextButton(
                        onClick = {
                            viewModel.eliminarMascota(indexToDelete)
                            showDialog = false
                            indexToDelete = -1
                        }
                    ) {
                        Text("Sí, eliminar", color = Color.Red)
                    }
                },
                dismissButton = {
                    TextButton(
                        onClick = {
                            showDialog = false
                            indexToDelete = -1
                        }
                    ) {
                        Text("Cancelar")
                    }
                }
            )
        }
    }}

@Composable
fun ScreenEditMascota(navController: NavController, viewModel: MascotaViewModel, index: Int) {
    val mascota = viewModel.listaMascotas[index]

    var nombre by remember { mutableStateOf(mascota.nombre) }
    var raza by remember { mutableStateOf(mascota.raza) }
    var tamaño by remember { mutableStateOf(mascota.tamaño) }
    var edad by remember { mutableStateOf(mascota.edad) }
    var fotoUrl by remember { mutableStateOf(mascota.fotoUrl) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(pastelYellow)
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text("Editar Mascota", style = MaterialTheme.typography.headlineMedium, fontWeight = FontWeight.Bold)

        @Composable
        fun textField(label: String, value: String, onValueChange: (String) -> Unit) {
            OutlinedTextField(
                value = value,
                onValueChange = onValueChange,
                label = { Text(label) },
                modifier = Modifier.fillMaxWidth(),
                colors = TextFieldDefaults.colors(
                    unfocusedContainerColor = Color.White,
                    focusedContainerColor = pastelPink,
                    cursorColor = Color.Black
                )
            )
        }

        textField("Nombre", nombre) { nombre = it }
        textField("Raza", raza) { raza = it }
        textField("Tamaño", tamaño) { tamaño = it }
        textField("Edad", edad) { edad = it }
        textField("Foto URL", fotoUrl) { fotoUrl = it }

        Spacer(modifier = Modifier.height(16.dp))

        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            Button(
                onClick = {
                    viewModel.editarMascota(index, Mascota(nombre, raza, tamaño, edad, fotoUrl))
                    navController.popBackStack()
                },
                colors = ButtonDefaults.buttonColors(containerColor = pastelBlue)
            ) {
                Text("Guardar", fontWeight = FontWeight.Bold)
            }

            Button(
                onClick = { navController.popBackStack() },
                colors = ButtonDefaults.buttonColors(containerColor = pastelPurple)
            ) {
                Text("Cancelar")
            }
        }
    }
}
