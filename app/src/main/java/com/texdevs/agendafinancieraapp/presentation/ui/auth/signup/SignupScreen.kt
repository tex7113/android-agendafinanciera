package com.texdevs.agendafinancieraapp.presentation.ui.auth.signup

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
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.texdevs.agendafinancieraapp.R
import com.texdevs.agendafinancieraapp.domain.model.User
import com.texdevs.agendafinancieraapp.presentation.ui.auth.viewmodel.AuthViewModel
import com.texdevs.agendafinancieraapp.ui.theme.SelectedField
import com.texdevs.agendafinancieraapp.ui.theme.UnselectedField
import com.texdevs.agendafinancieraapp.ui.theme.Yellow


@Composable
fun SignupScreen(
    navigateToLogin: () -> Unit,
    viewModel: AuthViewModel = AuthViewModel(),
) {

    val state by viewModel.signUpState

//    var email by remember { mutableStateOf("") }
//    var password by remember { mutableStateOf("") }
//    var name by remember { mutableStateOf("") }

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

        Text("Nombre", color = White, fontWeight = FontWeight.Bold, fontSize = 38.sp)
        OutlinedTextField(
            value = state.name,
            onValueChange = { viewModel.onNameChange(it) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 24.dp),
            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = UnselectedField,
                focusedContainerColor = SelectedField
            )
        )
        Spacer(Modifier.height(25.dp))

        Text("Correo electrónico", color = White, fontWeight = FontWeight.Bold, fontSize = 38.sp)
        OutlinedTextField(
            value = state.email,
            onValueChange = { viewModel.onEmailChange(it) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 24.dp),
            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = UnselectedField,
                focusedContainerColor = SelectedField
            )
        )
        Spacer(Modifier.height(25.dp))
        Text("Contraseña", color = White, fontWeight = FontWeight.Bold, fontSize = 38.sp)
        OutlinedTextField(
            value = state.password,
            onValueChange = { viewModel.onPasswordChange(it) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 24.dp),
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

        Spacer(Modifier.height(20.dp))

        Row {
            Spacer(modifier = Modifier.weight(1f))
            Button(onClick = {
                viewModel.registerUser(navigateToLogin)
//                auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener {
//                    if (it.isSuccessful) {
//                        val uid = it.result?.user?.uid ?: return@addOnCompleteListener
//                        val newUser = User(id = uid, email = email, password = password)
//                        createUser(newUser)
//                        navigateToLogin()
//                    }
//                }
            }
            ) {
                Text(text = "Registrarse", fontSize = 18.sp)
            }

            Spacer(modifier = Modifier.weight(1f))
        }
    }
}

fun createUser(newUser: User) {
    Firebase.database.reference.child("users").child(newUser.id!!).setValue(newUser)
}

