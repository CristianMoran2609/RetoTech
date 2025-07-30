package com.moran.retotecnico.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.moran.retotecnico.app.ui.theme.RetoTecnicoTheme
import com.moran.retotecnico.features.signup.presentation.SignUpScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RetoTecnicoTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { _ ->
                    SignUpScreen()
                }
            }
        }
    }
}