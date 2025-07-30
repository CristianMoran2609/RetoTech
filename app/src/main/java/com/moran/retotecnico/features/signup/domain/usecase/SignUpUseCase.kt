package com.moran.retotecnico.features.signup.domain.usecase

import com.moran.retotecnico.core.firebase.FirebaseAuthUi
import com.moran.retotecnico.features.signup.data.repository.SignUpRepositoryImpl

class SignUpUseCase(val signUpRepository: SignUpRepositoryImpl = SignUpRepositoryImpl()) {
    suspend fun invoke(email: String, password: String, result: (Result<FirebaseAuthUi>) -> Unit) =
        signUpRepository.signUp(email, password, result)
}