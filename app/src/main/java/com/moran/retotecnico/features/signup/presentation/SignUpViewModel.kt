package com.moran.retotecnico.features.signup.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.moran.retotecnico.features.signup.domain.usecase.SignUpUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class SignUpViewModel(
    val useCase: SignUpUseCase = SignUpUseCase()
) : ViewModel() {
    private val _state = MutableStateFlow(SignUpState())
    val state: StateFlow<SignUpState> = _state


    fun onEvent(event: SignUpEvent) {
        when (event) {
            is SignUpEvent.OnChangeEmail -> manageEventChangeEmail(event)
            is SignUpEvent.OnChangePassword -> manageEventChangePassword(event)
            SignUpEvent.OnClickSign -> signUpUser()
        }
    }

    private fun manageEventChangeEmail(event: SignUpEvent.OnChangeEmail) {
        _state.value = _state.value.copy(email = event.value)
    }

    private fun manageEventChangePassword(event: SignUpEvent.OnChangePassword) {
        _state.value = _state.value.copy(password = event.value)
    }

    private fun signUpUser() {
        val email = _state.value.email.trim()
        val password = _state.value.password.trim()

        viewModelScope.launch {
            useCase.invoke(email, password) { result ->
                if (result.isSuccess) {
                    println("Fue exitoso")
                } else {
                    println("Ocurrio un error")
                }
            }
        }
    }


}