package com.moran.retotecnico.features.signup.presentation

sealed class SignUpAction {
    object OnNavigateToHome : SignUpAction()
    object OnNavigateToLogin : SignUpAction()
}