package com.moran.retotecnico.features.login.presentation

sealed class LoginAction {
    object OnNavigateToHome : LoginAction()
    object OnBackPressed : LoginAction()
    data class ShowMessage(val message: String) : LoginAction()
}