package com.moran.retotecnico.features.signup.presentation

sealed class SignUpAction {
    object OnNavigateToHome : SignUpAction()
    object OnNavigateToLogin : SignUpAction()
    data class ShowMessage(val message: String) : SignUpAction()
}