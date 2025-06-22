package com.texdevs.agendafinancieraapp.presentation.ui.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.texdevs.agendafinancieraapp.presentation.model.Gasto
import com.texdevs.agendafinancieraapp.ui.theme.Black

@Composable
fun HomeScreen(
    totalDisponible: Double = 1250.0,
    gastosPendientes: List<Gasto> = listOf(),
    onAddGastoClick: () -> Unit = {},
    navigateToAddIcome: () -> Unit,
    navigateToAddExpense: () -> Unit
) {

    var showOptions by remember { mutableStateOf(false) }

    Scaffold(
        bottomBar = {
            BottomAppBar { }
        },
        floatingActionButton = {
            Column {
                if (showOptions) {
                    FloatingActionButton(
                        onClick = {
                            showOptions = false
                            navigateToAddIcome()
                        },
                        modifier = Modifier.padding(bottom = 8.dp)
                    ) {
                        Icon(Icons.Default.Add, contentDescription = "Agregar ingreso")
                    }

                    FloatingActionButton(
                        onClick = {
                            showOptions = false
                            navigateToAddExpense()
                        },
                        modifier = Modifier.padding(bottom = 8.dp)
                    ) {
                        Icon(Icons.Default.ShoppingCart, contentDescription = "Agregar gasto")
                    }
                }

                FloatingActionButton(onClick = { showOptions = !showOptions }) {
                    Icon(Icons.Default.Add, contentDescription = "Agregar")
                }
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            Row() {
//                Image(
//                    painter = painterResource(id = R.drawable.icon_app),
//                    contentDescription = "fotoperfil",
//                    modifier = Modifier.size(128.dp)
//                )
                Text(
                    text = "Hola User numero 1",
                    fontWeight = FontWeight.Bold,
                    fontSize = 26.sp,
                    modifier = Modifier.padding(16.dp)
                )
            }

            Text(
                modifier = Modifier.padding(horizontal = 8.dp),
                text = "Total Disponible",
                style = MaterialTheme.typography.titleMedium,
                fontSize = 24.sp
            )
            Text(
                modifier = Modifier.padding(horizontal = 8.dp),
                text = "$${"%.2f".format(totalDisponible)}",
                style = MaterialTheme.typography.headlineLarge,
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp
            )
            Spacer(modifier = Modifier.height(24.dp))

            Text(
                modifier = Modifier.padding(8.dp),
                text = "Gastos pendientes",
                style = MaterialTheme.typography.titleMedium
            )

            if (gastosPendientes.isEmpty()) {
                Text(
                    modifier = Modifier.padding(8.dp),
                    text = "No hay gastos pendientes.",
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.Gray
                )

                GastoItem()
            } else {
                LazyColumn(
                    modifier = Modifier.padding(8.dp)
                ) {
                    items(gastosPendientes) { gasto ->
                        GastoItem()
                    }
                }
            }
        }
    }
}

@Composable
fun GastoItem() {

    var showDialog by remember { mutableStateOf(false) }
    var gasto: Gasto
    gasto = Gasto(
        id = "1",
        nombre = "renta",
        monto = 1000.0,
        fecha = "2023-09-15",
        pagado = false
    )
    Card(
        colors = CardDefaults.cardColors(containerColor = Color.Gray),
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(12.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Text(gasto.nombre, fontWeight = FontWeight.Bold, fontSize = 20.sp)
                Text(
                    "Fecha: ${gasto.fecha}",
                    style = MaterialTheme.typography.bodySmall,
                    fontSize = 18.sp, modifier = Modifier.padding(top = 10.dp)
                )
            }
            Column {
                Text(
                    text = "-$${"%.2f".format(gasto.monto)}",
                    color = Color.Red,
                    style = MaterialTheme.typography.titleMedium,
                    fontSize = 20.sp
                )
                Button(onClick = { showDialog = true }) {
                    Text(text = "Pagar")
                }
            }
        }
        if (showDialog) {
            AlertDialog(
                onDismissRequest = { showDialog = false },
                title = { Text("Título del diálogo") },
                text = { Text("Este es el contenido del cuadro de diálogo.") },
                confirmButton = {
                    TextButton(onClick = { showDialog = false }) {
                        Text("Aceptar")
                    }
                },
                dismissButton = {
                    TextButton(onClick = { showDialog = false }) {
                        Text("Cancelar")
                    }
                }
            )
        }
    }
}