package com.texdevs.agendafinancieraapp.presentation.ui.addexpense

import android.app.DatePickerDialog
import android.os.Build
import android.widget.DatePicker
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.google.firebase.auth.FirebaseAuth
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Calendar

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun AddExpenseScreen(
    viewModel: AddExpenseViewModel,
    uid: String?,
    navController: NavHostController
) {
    val context = LocalContext.current
    val state = viewModel.state

    var date by remember { mutableStateOf(LocalDate.now()) }
    val dateFormatted = date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))
    viewModel.onDateChanged(dateFormatted)

    val calendar = Calendar.getInstance()
    val datePickerDialog = remember {
        DatePickerDialog(
            context,
            { _: DatePicker, year: Int, month: Int, dayOfMonth: Int ->
                date = LocalDate.of(year, month + 1, dayOfMonth)
            },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH),
        )
    }

    Column(modifier = Modifier
        .fillMaxSize()
        .systemBarsPadding()
        .padding(16.dp)) {
        Text("Agregar Gasto", style = MaterialTheme.typography.headlineSmall)

        Spacer(Modifier.height(16.dp))

        OutlinedTextField(
            value = state.description,
            onValueChange = { viewModel.onDescriptionChanged(it) },
            label = { Text("Descripción") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(Modifier.height(8.dp))

        OutlinedTextField(
            value = state.amount,
            onValueChange = {
                if (it.all { char -> char.isDigit() || char == '.' }) {
                    viewModel.onAmountChanged(it)
                }
            },
            label = { Text("Monto") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(Modifier.height(8.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("Fecha: $dateFormatted", style = MaterialTheme.typography.bodyLarge)
            Button(onClick = {
                datePickerDialog.show()
            }) {
                Icon(Icons.Default.DateRange, contentDescription = "Elegir fecha")
                Spacer(modifier = Modifier.width(4.dp))
                Text("Cambiar")
            }
        }

        Spacer(modifier = Modifier.weight(1f))

        Button(onClick = {
            if (uid != null) {
                viewModel.saveExpense(
                    userId = uid,
                    onSuccess = { navController.popBackStack() },
                    onError = {
                        Toast.makeText(context, it, Toast.LENGTH_LONG).show()
                    }
                )
            }
        },
            modifier = Modifier.fillMaxWidth())
        {
            Icon(Icons.Default.Check, contentDescription = "Guardar")
            Spacer(modifier = Modifier.width(8.dp))
            Text("Guardar ingreso")
        }
    }
}