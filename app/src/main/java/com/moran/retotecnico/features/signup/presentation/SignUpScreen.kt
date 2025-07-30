package com.moran.retotecnico.features.signup.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.moran.retotecnico.app.ui.theme.RetoTecnicoTheme
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch

@Composable
fun SignUpScreen(
    state: SignUpState,
    onEvent: (SignUpEvent) -> Unit,
    actionsFlow: SharedFlow<SignUpAction>,
    onGoToHome: () -> Unit,
    onGoToLogin: () -> Unit
) {
    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        actionsFlow.collect { action ->
            when (action) {
                is SignUpAction.OnNavigateToLogin -> onGoToLogin()
                is SignUpAction.OnNavigateToHome -> { /* Navegación pendiente */
                }

                is SignUpAction.ShowMessage -> {
                    coroutineScope.launch {
                        snackbarHostState.showSnackbar(action.message)
                    }
                }
            }
        }
    }

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 40.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Registrar", fontSize = 30.sp)
            Spacer(Modifier.height(30.dp))

            TextField(
                value = state.email,
                label = { Text("Ingrese su email") },
                onValueChange = { onEvent(SignUpEvent.OnChangeEmail(it)) },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Email,
                    imeAction = ImeAction.Done
                )
            )

            Spacer(Modifier.height(20.dp))

            TextField(
                value = state.password,
                label = { Text("Ingrese su contraseña") },
                onValueChange = { onEvent(SignUpEvent.OnChangePassword(it)) },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Password,
                    imeAction = ImeAction.Done
                )
            )

            Spacer(Modifier.height(20.dp))

            OutlinedButton(onClick = {
                onEvent(SignUpEvent.OnClickSign)
            }) {
                Text("SignUp")
            }

            Spacer(Modifier.height(25.dp))

            OutlinedButton(onClick = {
                onEvent(SignUpEvent.OnClickGoToLogin)
            }) {
                Text("Ya tengo un usuario")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SignUpPreview() {
    RetoTecnicoTheme {
        SignUpScreen(
            state = SignUpState(),
            onEvent = {},
            actionsFlow = MutableSharedFlow(),
            onGoToLogin = {},
            onGoToHome = {}
        )
    }
}