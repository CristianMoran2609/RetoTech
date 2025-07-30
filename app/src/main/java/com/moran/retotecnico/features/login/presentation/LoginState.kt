package com.moran.retotecnico.features.login.presentation

data class LoginState(
    val error: String = String(),
    val email: String = String(),
    val password: String = String()
) {
}