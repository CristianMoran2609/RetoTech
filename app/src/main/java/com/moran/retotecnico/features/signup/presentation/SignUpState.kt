package com.moran.retotecnico.features.signup.presentation

data class SignUpState(
    val error: String = String(),
    val email: String = String(),
    val password: String = String()
) {
}