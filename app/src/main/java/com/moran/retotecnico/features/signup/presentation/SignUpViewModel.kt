package com.moran.retotecnico.features.signup.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.moran.retotecnico.features.signup.domain.usecase.SignUpUseCase
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class SignUpViewModel(
    val useCase: SignUpUseCase = SignUpUseCase()
) : ViewModel() {
    private val _actionFlow = MutableSharedFlow<SignUpAction>(replay = 0)
    val action: SharedFlow<SignUpAction> = _actionFlow

    private val _state = MutableStateFlow(SignUpState())
    val state: StateFlow<SignUpState> = _state


    fun onEvent(event: SignUpEvent) {
        when (event) {
            is SignUpEvent.OnChangeEmail -> manageEventChangeEmail(event)
            is SignUpEvent.OnChangePassword -> manageEventChangePassword(event)
            SignUpEvent.OnClickSign -> signUpUser()
            SignUpEvent.OnClickGoToLogin -> sendAction(SignUpAction.OnNavigateToLogin)
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

        if (email.isEmpty() && password.isEmpty()) {
            sendAction(SignUpAction.ShowMessage("Debe ingresar un email y contraseña"))
            return
        }

        viewModelScope.launch {
            useCase.invoke(email, password) { result ->
                if (result.isSuccess) {
                    sendAction(SignUpAction.ShowMessage("Registro existoso"))
                    sendAction(SignUpAction.OnNavigateToHome)
                } else {
                    sendAction(SignUpAction.ShowMessage(result.exceptionOrNull()?.message.orEmpty()))
                }
            }
        }
    }

    private fun sendAction(action: SignUpAction) {
        viewModelScope.launch {
            _actionFlow.emit(action)
        }
    }
}