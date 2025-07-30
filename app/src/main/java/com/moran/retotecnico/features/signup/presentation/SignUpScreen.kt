package com.moran.retotecnico.features.signup.presentation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.moran.retotecnico.app.ui.theme.RetoTecnicoTheme

@Composable
fun SignUpScreen(
    viewModel: SignUpViewModel = viewModel()
) {


    val state = viewModel.state.collectAsState().value

    Column(Modifier.padding(20.dp), horizontalAlignment = Alignment.CenterHorizontally) {
        Text("Registrar", fontSize = 30.sp)
        Spacer(Modifier.height(30.dp))
        TextField(
            state.email, label = {
                Text("Ingrese su email")
            },
            onValueChange = { newValue ->
                viewModel.onEvent(SignUpEvent.OnChangeEmail(newValue))
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email,
                imeAction = ImeAction.Done
            )
        )
        Spacer(Modifier.height(20.dp))
        TextField(
            state.password, label = {
                Text("Ingrese su contraseña")
            },
            onValueChange = { newValue ->
                viewModel.onEvent(SignUpEvent.OnChangePassword(newValue))
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Done
            )
        )
        Spacer(Modifier.height(20.dp))
        OutlinedButton(onClick = {
            viewModel.onEvent(SignUpEvent.OnClickSign)
        }) {
            Text("SignUp")
        }

        Spacer(Modifier.height(20.dp))
        Text("Ya tengo un usuario", modifier = Modifier.clickable {

        })

    }
}

@Preview(showBackground = true)
@Composable
fun LoginPreview() {
    RetoTecnicoTheme {
        SignUpScreen()
    }
}