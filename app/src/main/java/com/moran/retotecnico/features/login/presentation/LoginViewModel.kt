package com.moran.retotecnico.features.login.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.moran.retotecnico.features.login.domain.usecase.AuthUseCase
import com.moran.retotecnico.features.signup.presentation.SignUpAction
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class LoginViewModel(
    val useCase: AuthUseCase = AuthUseCase()
) : ViewModel() {
    private val _actionFlow = MutableSharedFlow<LoginAction>(replay = 0)
    val action: SharedFlow<LoginAction> = _actionFlow

    private val _state = MutableStateFlow(LoginState())
    val state: StateFlow<LoginState> = _state

    fun onEvent(event: LoginEvent) {
        when (event) {
            is LoginEvent.OnChangeEmail -> manageEventChangeEmail(event)
            is LoginEvent.OnChangePassword -> manageEventChangePassword(event)
            LoginEvent.OnClickLogin -> login()
            LoginEvent.OnClickOnBackPressed -> sendAction(LoginAction.OnBackPressed)
        }
    }

    private fun manageEventChangeEmail(event: LoginEvent.OnChangeEmail) {
        _state.value = _state.value.copy(email = event.value)
    }

    private fun manageEventChangePassword(event: LoginEvent.OnChangePassword) {
        _state.value = _state.value.copy(password = event.value)
    }

    private fun login() {
        val email = _state.value.email.trim()
        val password = _state.value.password.trim()

        if (email.isEmpty() && password.isEmpty()) {
            sendAction(LoginAction.ShowMessage("Debe ingresar un email y contraseña"))
            return
        }

        viewModelScope.launch {
            useCase.invoke(email, password) { result ->
                if (result.isSuccess) {
                    sendAction(LoginAction.ShowMessage("Login existoso"))
                    sendAction(LoginAction.OnNavigateToHome)
                } else {
                    sendAction(LoginAction.ShowMessage(result.exceptionOrNull()?.message.orEmpty()))
                }
            }
        }
    }

    private fun sendAction(action: LoginAction) {
        viewModelScope.launch {
            _actionFlow.emit(action)
        }
    }
}