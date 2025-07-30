package com.moran.retotecnico.features.login.data.repository

import com.moran.retotecnico.core.firebase.CustomFirebaseAuth
import com.moran.retotecnico.core.firebase.FirebaseAuthUi
import com.moran.retotecnico.features.login.domain.repository.LoginRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class LoginRepositoryImpl(
    val customFirebaseAuth: CustomFirebaseAuth = CustomFirebaseAuth()
) : LoginRepository {
    override suspend fun login(
        email: String,
        password: String,
        result: (Result<FirebaseAuthUi>) -> Unit
    ) {
        withContext(Dispatchers.IO) {
            customFirebaseAuth.auth(email, password, result)
        }
    }
}