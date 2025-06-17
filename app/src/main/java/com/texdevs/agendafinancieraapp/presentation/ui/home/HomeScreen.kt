package com.texdevs.agendafinancieraapp.presentation.ui.home

import androidx.compose.runtime.Composable
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.ScaffoldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.texdevs.agendafinancieraapp.R
import com.texdevs.agendafinancieraapp.presentation.model.Gasto
import com.texdevs.agendafinancieraapp.ui.theme.Black
import com.texdevs.agendafinancieraapp.ui.theme.Green
import com.texdevs.agendafinancieraapp.ui.theme.ShapeButton
import com.texdevs.agendafinancieraapp.ui.theme.Yellow
import com.texdevs.agendafinancieraapp.ui.theme.Yellowpal

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    totalDisponible: Double = 1250.0,
    gastosPendientes: List<Gasto> = listOf(),
    onAddGastoClick: () -> Unit = {}
) {

    Scaffold(
        topBar = {
            TopAppBar(
                colors = topAppBarColors(
                    containerColor = Color.Yellow,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                title = {
                    Text("Agenda Financiera", fontWeight = FontWeight.Bold)
                }
            )
        },
        bottomBar = {
            BottomAppBar(
                containerColor = Color.Yellow,
            ) {
                Text(
                    modifier = Modifier
                        .fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    text = "Bottom app bar",
                )
            }
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { }) {
                Icon(Icons.Default.Add, contentDescription = "Add")
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
//                    contentDescription = "foto perfil",
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
                color = Color.Gray,
                fontSize = 24.sp
            )
            Text(
                modifier = Modifier.padding(horizontal = 8.dp),
                text = "$${"%.2f".format(totalDisponible)}", style = MaterialTheme.typography.headlineLarge,
                fontWeight = FontWeight.Bold, fontSize = 24.sp
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
            } else {
                LazyColumn {
                    items(gastosPendientes) { gasto ->
                        GastoItem(gasto)
                    }
                }
            }
        }
    }
}

@Composable
fun GastoItem(gasto: Gasto) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(12.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Text(gasto.nombre, fontWeight = FontWeight.Bold)
                Text("Fecha: ${gasto.fecha}", style = MaterialTheme.typography.bodySmall)
            }
            Text(
                text = "-$${"%.2f".format(gasto.monto)}",
                color = Color.Red,
                style = MaterialTheme.typography.titleMedium
            )
        }
    }
}


