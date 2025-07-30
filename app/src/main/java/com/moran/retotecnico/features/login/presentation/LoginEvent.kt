package com.moran.retotecnico.features.login.presentation

sealed class LoginEvent {
    data class OnChangeEmail(val value: String) : LoginEvent()
    data class OnChangePassword(val value: String) : LoginEvent()
    object OnClickLogin : LoginEvent()
    object OnClickOnBackPressed : LoginEvent()
}