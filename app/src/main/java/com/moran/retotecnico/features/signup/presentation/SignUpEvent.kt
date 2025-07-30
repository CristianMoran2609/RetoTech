package com.moran.retotecnico.features.signup.presentation

sealed class SignUpEvent {
    data class OnChangeEmail(val value: String) : SignUpEvent()
    data class OnChangePassword(val value: String) : SignUpEvent()
    object OnClickSign : SignUpEvent()
}