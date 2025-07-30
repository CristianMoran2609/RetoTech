package com.moran.retotecnico.features.login.domain.repository

import com.moran.retotecnico.core.firebase.FirebaseAuthUi

interface LoginRepository {

    suspend fun login(
        email: String,
        password: String,
        result: (Result<FirebaseAuthUi>) -> Unit
    )

}