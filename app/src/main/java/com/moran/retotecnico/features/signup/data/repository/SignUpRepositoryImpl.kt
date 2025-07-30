package com.moran.retotecnico.features.signup.data.repository

import com.moran.retotecnico.core.firebase.CustomFirebaseAuth
import com.moran.retotecnico.core.firebase.FirebaseAuthUi
import com.moran.retotecnico.features.signup.domain.repository.SignUpRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class SignUpRepositoryImpl() : SignUpRepository {
    val customFirebaseAuth: CustomFirebaseAuth = CustomFirebaseAuth()
    override suspend fun signUp(
        email: String,
        password: String,
        result: (Result<FirebaseAuthUi>) -> Unit
    ) {
        withContext(Dispatchers.IO) {
            customFirebaseAuth.signUp(email, password, result)
        }
    }
}