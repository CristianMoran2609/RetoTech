package com.moran.retotecnico.features.login.domain.usecase

import com.moran.retotecnico.core.firebase.FirebaseAuthUi
import com.moran.retotecnico.features.login.data.repository.LoginRepositoryImpl

class AuthUseCase(val loginRepository: LoginRepositoryImpl = LoginRepositoryImpl()) {
    suspend fun invoke(email: String, password: String, result: (Result<FirebaseAuthUi>) -> Unit) =
        loginRepository.login(email, password, result)
}