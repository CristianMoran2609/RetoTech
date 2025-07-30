package com.moran.retotecnico.features.signup.domain.repository

import com.moran.retotecnico.core.firebase.FirebaseAuthUi

interface SignUpRepository {

    suspend fun signUp(email: String, password: String, result: (Result<FirebaseAuthUi>) -> Unit)

}