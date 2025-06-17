package com.texdevs.agendafinancieraapp.presentation.ui.auth.login

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.texdevs.agendafinancieraapp.R
import com.texdevs.agendafinancieraapp.presentation.ui.auth.viewmodel.AuthViewModel
import com.texdevs.agendafinancieraapp.ui.theme.SelectedField
import com.texdevs.agendafinancieraapp.ui.theme.UnselectedField
import com.texdevs.agendafinancieraapp.ui.theme.Yellow

@Composable
fun LoginScreen(
    navigateToHome: () -> Unit,
    viewModel: AuthViewModel = AuthViewModel()
) {

    val state by viewModel.loginState

//    var email by remember { mutableStateOf("") }
//    var password by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Yellow)
            .padding(horizontal = 32.dp)
    ) {

        Row(modifier = Modifier.padding(vertical = 24.dp)) {
            Icon(
                painter = painterResource(id = R.drawable.ic_back),
                contentDescription = null,
                tint = White,
                modifier = Modifier
                    .padding(vertical = 24.dp)
                    .size(28.dp)
            )
            Spacer(modifier = Modifier.weight(1f))
        }

        Text("Email", color = White, fontWeight = FontWeight.Bold, fontSize = 40.sp)
        OutlinedTextField(
            value = state.email,
            onValueChange =  {viewModel.onLoginEmailChange(it)},
            modifier = Modifier.fillMaxWidth(),
            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = UnselectedField,
                focusedContainerColor = SelectedField
            )
        )
        Spacer(Modifier.height(48.dp))
        Text("Password", color = White, fontWeight = FontWeight.Bold, fontSize = 40.sp)
        OutlinedTextField(
            value = state.password,
            onValueChange = { viewModel.onLoginPasswordChange(it) },
            modifier = Modifier.fillMaxWidth(),
            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = UnselectedField,
                focusedContainerColor = SelectedField
            ),
            visualTransformation = PasswordVisualTransformation(), // Oculta la contraseña
            singleLine = true
        )
        Spacer(Modifier.height(10.dp))

        state.error?.let {
            Text(text = it, color = Color.Red)
        }
        if (state.isLoading) {
            Spacer(modifier = Modifier.height(8.dp))
            LinearProgressIndicator(modifier = Modifier.fillMaxWidth())
        }

        Spacer(Modifier.height(48.dp))

        Row {
            Spacer(modifier = Modifier.weight(1f))
            Button(onClick = { viewModel.loginUser(navigateToHome) }) {
                Text(text = "Login", fontSize = 18.sp)
            }
            Spacer(modifier = Modifier.weight(1f))
        }
    }
}
